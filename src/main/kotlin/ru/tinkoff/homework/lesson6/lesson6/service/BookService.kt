package ru.tinkoff.homework.lesson6.lesson6.service

import org.springframework.stereotype.Service
import ru.tinkoff.homework.lesson6.lesson6.model.Book
import ru.tinkoff.homework.lesson6.lesson6.service.client.BookClient
import ru.tinkoff.homework.lesson6.lesson6.service.repository.BookDatabase

@Service
class BookService(private val bookClient: BookClient,
                  private val repository: BookDatabase) {

    fun addBook(requiredBookIsbn: String): Book {
        // BookClient is a service to enrich data
        val book = bookClient.getBook(requiredBookIsbn)

        // If request to another service was not successful
        // We definitely don't want to return it
        checkNotNull(book) { "Client was not able to found a book with given $requiredBookIsbn!" }

        // Validation of data
        validateBook(book)
        repository.saveBook(book)

        // Return the book sample from a database
        return getBookByIsbn(requiredBookIsbn)
    }

    fun validateBook(book: Book) {
        // The ISBN is a numeric commercial book identifier that is intended to be unique.
        // It is always 10 or 13 symbols length, it depends on standard
        require(book.isbn.length == 10 || book.isbn.length == 13) { "The ISBN length is always 10 or 13 symbols" }

        // We don't want to store empty strings
        require(book.title.isNotEmpty()) { "Book title must be a non-empty string" }
        require(book.author.isNotEmpty()) { "Book author must be a non-empty string" }
    }

    fun getBookByIsbn(isbn: String): Book {
        // We go to DB to get sample
        val book = repository.getBookByIsbn(isbn)

        // We don't want to return a null
        return requireNotNull(book) { "Book with given $isbn is not present in DB!" }
    }

    fun findBookByTitle(name: String, pageLen: Int, page: Int): List<Book> {
        // Obviously, params have to be positive
        require(page > 0) { "Page must be a positive number" }
        require(pageLen > 0) { "PageLen must be a positive number" }

        // Go to DB
        return repository.findBookByTitle(name, pageLen, page)
    }
}