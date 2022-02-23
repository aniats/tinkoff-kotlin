package ru.tinkoff.homework.lesson1


class Flower(override val name: String, private var isBisexual: Boolean = true) : Plant {
    /*
    * This example demonstrates a possibility to add an additional method
    * beside the interface's methods.
    * In this example: an additional property and an additional method
    */
    fun breed() {
        if (isBisexual) {
            println("$name has sprouted! Look out for new leaves!")
        } else {
            println("You need $name of other sex in order to breed")
        }
    }

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