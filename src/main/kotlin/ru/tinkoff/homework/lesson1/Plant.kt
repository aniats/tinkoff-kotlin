package ru.tinkoff.homework.lesson1

interface Plant {
    val name: String

    fun dry()

    fun blossom() {
        println("This plant cannot blossom")
    }
}