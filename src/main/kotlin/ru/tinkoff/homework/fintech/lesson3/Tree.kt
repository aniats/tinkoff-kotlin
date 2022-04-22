package ru.tinkoff.homework.lesson3

import kotlin.math.max
import kotlin.math.min

class Tree(override val name: String, private val height: Int, private var hydration: Int = 100) : Plant {
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
    fun getHeightInfo() {
        println("The tree is $height cm. Please bear it in mind when planning your planting location")
    }

    override fun dry() {
        println("$name is running out of water. Save the tree before it is too late!")
    }

    override fun getHydrationLevel(): Int {
        return hydration
    }
}