package ru.tinkoff.homework.lesson3

interface Plant {
    val name: String

    fun dry()

    fun blossom() {
        println("This plant cannot blossom")
    }

    /*
     * I added a new method, because comparing strings in asserts does not seem
     * like a good idea for unit tests
     */
    fun getHydrationLevel(): Int
}