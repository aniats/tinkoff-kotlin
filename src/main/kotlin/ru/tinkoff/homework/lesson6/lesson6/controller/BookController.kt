package ru.tinkoff.homework.lesson6.lesson6.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import ru.tinkoff.homework.lesson6.lesson6.model.Book
import ru.tinkoff.homework.lesson6.lesson6.service.BookService
import java.time.Year

@RestController
@RequestMapping("/")
class BookController(private val bookService: BookService) {
    @PostMapping("/add")
    fun addBook(
        @RequestBody isbn: String
    ): Book? =
        bookService.addBook(isbn)

    @GetMapping("/get/{isbn}")
    fun getBookByIsbn(@PathVariable isbn: String): Book =
        bookService.getBookByIsbn(isbn)

    @GetMapping("/find")
    fun findBookByTitle(
        @RequestParam name: String,
        @RequestParam pageLen: Int,
        @RequestParam page: Int
    ): List<Book> =
        bookService.findBookByTitle(name, pageLen, page)
}