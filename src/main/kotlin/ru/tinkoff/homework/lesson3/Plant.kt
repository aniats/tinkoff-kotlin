package ru.tinkoff.homework.lesson3

interface Plant {
    val name: String

    fun dry()

    fun blossom() {
        println("This plant cannot blossom")
    }

    fun hydration(previous: Int, days: Int): Int {
        val res = previous - days * 10
        if (res < 0) return 0
        return res
    }
}