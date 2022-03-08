package ru.tinkoff.homework.lesson3

import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlantPotTest {
    @Test
    fun `Check return value in isWaterNeeded` () {
        val tree = mockk<Tree>()
        every { tree.getHydrationLevel() } returns 50;

        val flower = mockk<Flower>()
        every { flower.getHydrationLevel() } returns 20;

        val plantPotFalse = PlantPot(tree, "Yellow pot")
        val plantPotTrue = PlantPot(flower, "Yellow pot")

        assertAll(
            { assertEquals(false, plantPotFalse.isWaterNeeded()) },
            { assertEquals(true, plantPotTrue.isWaterNeeded()) }
        )
    }
}