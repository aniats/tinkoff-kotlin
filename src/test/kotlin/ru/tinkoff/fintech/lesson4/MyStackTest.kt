package ru.tinkoff.fintech.lesson4

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class MyStackTest {
    @Test
    fun testStackEmptyPopThrows() {
        val myStack = MyStack<Int>()
        try {
            myStack.pop()
        } catch (e: Exception) {
            assertEquals(true, e is NoSuchElementException)
        }
    }

    @Test
    fun testStackEmptyPeekThrows() {
        val myStack = MyStack<Int>()
        try {
            myStack.peek()
        } catch (e: Exception) {
            assertEquals(true, e is NoSuchElementException)
        }
    }

    @Test
    fun testStackPopComplexScenario() {
        val myStack = MyStack<Int>()
        myStack.push(1)
        myStack.push(2)
        myStack.push(3)
        assertEquals(3, myStack.pop())
        assertEquals(2, myStack.pop())
        assertEquals(1, myStack.pop())
    }

    @Test
    fun testInitializers() {
        val sData = listOf(5, 4, 3, 2, 1)
        val s = myStackOf(1, 2, 3, 4, 5)
        sData.forEach { elem -> check(elem == s.pop()) }
    }
}
