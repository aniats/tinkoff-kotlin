package ru.tinkoff.homework.lesson3

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlantPotTest {
    /*
     * Mock tests for function isWaterNeeded
     */
    @Test
    fun `Check isWaterNeeded for Tree class`() {
        val tree = mockk<Tree>()
        every { tree.getHydrationLevel() } returns 50;

        val plantPotFalse = PlantPot(tree, "Yellow pot")

        assertEquals(false, plantPotFalse.isWaterNeeded())

        verify(exactly = 1) { plantPotFalse.isWaterNeeded() }
    }

    @Test
    fun `Check isWaterNeeded for Flower class`() {
        val flower = mockk<Flower>()
        every { flower.getHydrationLevel() } returns 20;

        val plantPotTrue = PlantPot(flower, "Yellow pot")

        assertEquals(true, plantPotTrue.isWaterNeeded())

        verify(exactly = 1) { plantPotTrue.isWaterNeeded() }
    }
}
