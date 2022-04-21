package ru.tinkoff.homework.fintech.lesson1

fun main() {
    val dragonTree = Tree("The Dragon Tree", 90)
    val pine = Tree("Pine tree", 1500)
    val spathiphyllum = Flower("Spathiphyllum", false)

    /*
     * Demonstrating that an overridden method works in a derived class
     * instead of default implementation:
     */
    println("Demonstrating an overridden method:")
    (spathiphyllum as Plant).blossom()

    println()
    val favouritePlants: List<Plant> = listOf(
        pine,
        spathiphyllum,
        dragonTree
    )

    println("Demonstrating polymorphism:")
    favouritePlants.forEach { plant -> run { plant.blossom(); plant.dry(); println() } }

    println("Calling additional method, not present on the interface:")
    dragonTree.getHeightInfo()

    println()

    println("Calling additional method, not present on the interface:")
    spathiphyllum.breed()

    println()

    println("Demonstrating aggregation relation between pots and plants:")
    val livingRoomPot = PlantPot(spathiphyllum, "Yellow Pot")
    livingRoomPot.makePlantBlossom()
}