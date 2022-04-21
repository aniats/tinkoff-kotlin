package ru.tinkoff.homework.fintech.lesson3

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class FlowerTest {
    /*
     * Unit tests for function hydrationLevel
     */
    @Test
    fun `Check hydrationLevel for negative values`() {
        val roseNegative = Flower("Rose", false, -1)
        assertEquals(0, roseNegative.getHydrationLevel())
    }

    @Test
    fun `Check hydrationLevel for 100% of hydration`() {
        val roseFull = Flower("Rose")
        assertEquals(100, roseFull.getHydrationLevel())
    }

    @Test
    fun `Check hydrationLevel for 50% of hydration`() {
        val roseHalf = Flower("Rose", false, 50)
        assertEquals(50, roseHalf.getHydrationLevel())
    }

    @Test
    fun `Check hydrationLevel for overflow`() {
        val roseOverflow = Flower("Rose", false, 1000)
        assertEquals(100, roseOverflow.getHydrationLevel())
    }
}