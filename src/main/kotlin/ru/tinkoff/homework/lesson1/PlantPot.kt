package ru.tinkoff.homework.lesson1

class PlantPot(private val plant: Plant, private val potDesc: String) {
    fun getInfo() {
        println("There is a ${plant.name} growing in this $potDesc")
    }
}