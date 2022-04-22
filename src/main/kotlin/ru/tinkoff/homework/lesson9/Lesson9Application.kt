package ru.tinkoff.homework.lesson9

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class Lesson9Application

fun main(args: Array<String>) {
	runApplication<Lesson9Application>(*args)
}
