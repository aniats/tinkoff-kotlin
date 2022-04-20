package ru.tinkoff.homework.lesson6.lesson6.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import com.ninjasquad.springmockk.SpykBean
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.FeatureSpec
import io.mockk.every
import ru.tinkoff.homework.lesson6.lesson6.model.Book
import ru.tinkoff.homework.lesson6.lesson6.service.client.BookClient
import ru.tinkoff.homework.lesson6.lesson6.service.repository.BookDatabase
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.Test
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.ints.exactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.ResultActionsDsl
import kotlin.text.Charsets.UTF_8

@SpringBootTest()
@AutoConfigureMockMvc
class BookServiceTest(private val mockMvc: MockMvc, private val objectMapper: ObjectMapper): FeatureSpec() {
    @SpykBean
    private lateinit var repository: BookDatabase

    @MockkBean
    private lateinit var bookInfo: BookClient

    override fun extensions(): List<Extension> = listOf(SpringExtension)


    // Sample data to test
    private companion object {
        val book1 = Book("Richard Thaler", "Nudge", "014311526X")
        val bookToAdd = Book("Mike Resnick", "The Buntline Special", "1616142499")
        val book3 = Book("Malcolm Gladwell", "Outliers: The Story of Success", "0316017922")
        val notPresent = Book("Daniel Kahneman", "Thinking, Fast and Slow", "0374275637")
        val notExistingTitle = "The Wealth of Nations"
        val wrongISBN = "123345"
    }

    override fun beforeSpec(spec: Spec) {
        repository.saveBook(book1)
        repository.saveBook(book3)
        every { bookInfo.getBook(book3.isbn) } returns book3
        every { bookInfo.getBook(bookToAdd.isbn) } returns bookToAdd
    }

    override fun afterEach(testCase: TestCase, result: TestResult) {
        clearAllMocks()
    }

    init {
        feature("Add a book to db") {
            scenario("OK") {
                // We want to make sure that book is not in DB
                getStatusCodeGetBookByIsbn(bookToAdd.isbn) shouldBe HttpStatus.BAD_REQUEST.value()

                // Attempt to add it to DB
                val result = addBook(bookToAdd.isbn)
                result shouldBe bookToAdd

                // Check if book really was added to DB
                getStatusCodeGetBookByIsbn(bookToAdd.isbn) shouldBe HttpStatus.OK.value()
            }
            scenario("Incorrect ISBN, unsuccessful validation") {
                getStatusCodeAddBook(wrongISBN) shouldBe HttpStatus.INTERNAL_SERVER_ERROR.value()
            }
            scenario("Book is already present") {
                // Checking if we are not able to add existing book again
                getStatusCodeAddBook(book1.isbn) shouldBe HttpStatus.INTERNAL_SERVER_ERROR.value()
            }
        }

        feature("Get a paginated list with books") {
            scenario("Found successfully") {
                val expected = listOf(book1)
                val result = findBookByTitle(book1.title, 1, 1)
                result shouldBe expected
            }
            scenario("Non-existing title of the book") {
                val expected = emptyList<Book>()
                val result = findBookByTitle(notExistingTitle, 1, 1)
                expected shouldBe result
            }
            scenario("Wrong negative values in params") {
                // Testing validation with negative params
                getStatusCodeFindBookByTitle(notExistingTitle, -1, -1) shouldBe
                        HttpStatus.BAD_REQUEST.value()
                getStatusCodeFindBookByTitle(book1.title, -1, -1) shouldBe
                        HttpStatus.BAD_REQUEST.value()
            }
        }
        feature("Get a book from db") {
            scenario("OK") {
                // Try to access existing book from DB
                getStatusCodeGetBookByIsbn(book1.isbn) shouldBe HttpStatus.OK.value()
                val result = getBookByIsbn(book1.isbn)

                // Check if the returned book is the correct one
                result shouldBe book1
            }
            scenario("Fail, book is not present") {
                getStatusCodeGetBookByIsbn(notPresent.isbn) shouldBe HttpStatus.BAD_REQUEST.value()
            }
        }
    }

    private inline fun <reified T> ResultActionsDsl.readResponse(expectedStatus: HttpStatus = HttpStatus.OK): T = this
        .andExpect { status { isEqualTo(expectedStatus.value()) } }
        .andReturn().response.getContentAsString(UTF_8)
        .let { if (T::class == String::class) it as T else objectMapper.readValue(it) }

    private fun addBook(isbn: String): Book? =
        mockMvc.post("/add") { contentType = MediaType.TEXT_PLAIN; content = isbn }.readResponse()

    private fun getStatusCodeAddBook(isbn: String): Int =
        mockMvc.post("/add") { contentType = MediaType.TEXT_PLAIN; content = isbn }
            .andReturn().response.status

    private fun getBookByIsbn(isbn: String): Book =
        mockMvc.get("/get/{isbn}", isbn).readResponse()

    private fun getStatusCodeGetBookByIsbn(isbn: String): Int =
        mockMvc.get("/get/{isbn}", isbn).andReturn().response.status

    private fun findBookByTitle(name: String, pageLen: Int, page: Int): List<Book> =
        mockMvc.get("/find?name={name}&pageLen={pageLen}&page={page}", name, pageLen, page).readResponse()

    private fun getStatusCodeFindBookByTitle(name: String, pageLen: Int, page: Int): Int =
        mockMvc.get("/find?name={name}&pageLen={pageLen}&page={page}", name, pageLen, page)
            .andReturn().response.status
}