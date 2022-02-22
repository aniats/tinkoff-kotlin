package ru.tinkoff.homework.lesson1


class Flower(override val name: String) : Plant {
    /*
    * This example demonstrates an implementation of the overriding method in the interface
    */
    override fun blossom() {
        println("$name is blossoming! The flower smells pleasantly fragrant")
    }

    /*
     * This example demonstrates an implementation of an abstract method in the interface
     */
    override fun dry() {
        println("$name is drying out. Use the watering can to water it")
    }
}