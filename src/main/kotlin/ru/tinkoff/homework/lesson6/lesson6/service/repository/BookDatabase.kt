package ru.tinkoff.homework.lesson6.lesson6.service.repository

import org.springframework.stereotype.Repository
import ru.tinkoff.homework.lesson6.lesson6.model.Book
import java.util.concurrent.ConcurrentHashMap

@Repository
class BookDatabase {
    private val books: ConcurrentHashMap<String, Book> = ConcurrentHashMap()

    fun saveBook(book: Book) {
        books[book.isbn] = book;
    }

    fun getBookByIsbn(isbn: String): Book? {
        return books[isbn];
    }

    // Get books with certain title with pagination
    fun findBookByTitle(title: String, pageLen: Int, page: Int): List<Book> =
        books.asSequence()
            .map { it.value }
            .filter { it.title == title }
            .drop(pageLen * (page - 1))
            .take(pageLen)
            .toList()
}