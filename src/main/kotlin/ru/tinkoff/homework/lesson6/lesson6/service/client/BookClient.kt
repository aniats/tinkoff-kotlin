package ru.tinkoff.homework.lesson6.lesson6.service.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import ru.tinkoff.homework.lesson6.lesson6.model.Book

// Fake client which pretends to enrich book's data by ISBN

@Service
class BookClient(
    private val restTemplate: RestTemplate,
    @Value("\${book.address}") private val bookAddress: String
) {

    fun getBook(isbn: String): Book? {
        return try {
            restTemplate.getForObject<Book>("$bookAddress$BOOK_ISBN", isbn)
        } catch (e: HttpClientErrorException.NotFound) {
            null
        }
    }
}

private const val BOOK_ISBN = "/get?isbn={isbn}"