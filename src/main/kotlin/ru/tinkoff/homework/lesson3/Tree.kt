package ru.tinkoff.homework.lesson3

class Tree(override val name: String, private val height: Int) : Plant {
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
}