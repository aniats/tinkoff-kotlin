package ru.tinkoff.homework.fintech.lesson3

class PlantPot(private val plant: Plant, private val potDesc: String) {
    fun getInfo() {
        println("There is a ${plant.name} growing in this $potDesc")
    }

    fun makePlantBlossom() {
        println("You added plant fertilizer. Let's see what happens next")
        plant.blossom()
    }

    fun isWaterNeeded(): Boolean {
        val level = plant.getHydrationLevel()
        return level < 45
    }
}