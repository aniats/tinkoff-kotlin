package ru.tinkoff.homework.lesson3

import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class FlowerTest {
    @Test
    fun `check return value in hydrationLevel` () {
        val roseFull = Flower("Rose")
        val roseHalf = Flower("Rose", false, 50)
        val roseNegative = Flower("Rose", false, -1)
        val roseOverflow = Flower("Rose", false, 1000)

        assertAll(
            { assertEquals(100, roseFull.getHydrationLevel()) },
            { assertEquals(50, roseHalf.getHydrationLevel()) },
            { assertEquals(0, roseNegative.getHydrationLevel()) },
            { assertEquals(100, roseOverflow.getHydrationLevel()) },
        )
    }
}