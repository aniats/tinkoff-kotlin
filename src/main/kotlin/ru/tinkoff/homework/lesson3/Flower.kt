package ru.tinkoff.homework.lesson3

import kotlin.math.max
import kotlin.math.min


class Flower(override val name: String, private var isBisexual: Boolean = true, private var hydration: Int = 100) : Plant {
    init {
        /*
         * Bring `hydration` parameter to range 0-100.
         * TODO throw an exception instead.
         */
        hydration = max(hydration, 0)
        hydration = min(hydration, 100)
    }
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

    override fun getHydrationLevel(): Int {
        return hydration
    }
}