package ru.tinkoff.homework.lesson6.lesson6

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableWebMvc
class Lesson6Application

fun main(args: Array<String>) {
	runApplication<Lesson6Application>(*args)
}
