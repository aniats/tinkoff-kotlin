package ru.tinkoff.homework.fintech.lesson4

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import ru.tinkoff.homework.fintech.lesson4.MyQueue
import ru.tinkoff.homework.fintech.lesson4.myQueueOf

class MyQueueTest {
    @Test
    fun testEmptyQueueElementThrows() {
        val myQueue = MyQueue<Int>()
        try {
            myQueue.element()
        } catch (e: Exception) {
            assertEquals(true, e is NoSuchElementException)
        }
    }

    @Test
    fun testQueueOneElement() {
        val myQueue = MyQueue<Int>()
        myQueue.offer(1)
        assertEquals(1, myQueue.element())
    }

    @Test
    fun testEmptyQueueRemoveThrows() {
        val myQueue = MyQueue<Int>()
        try {
            myQueue.remove()
        } catch (e: Exception) {
            assertEquals(true, e is NoSuchElementException)
        }
    }

    @Test
    fun testRemoveComplexScenario() {
        val myQueue = MyQueue<Int>()
        myQueue.offer(1)
        myQueue.offer(2)
        myQueue.offer(3)
        assertEquals(1, myQueue.remove())
        assertEquals(2, myQueue.remove())
        assertEquals(3, myQueue.remove())
    }


    @Test
    fun testEmptyQueuePeekIsNull() {
        val myQueue = MyQueue<Int>()
        assertEquals(null, myQueue.peek())
    }

    @Test
    fun testPeekComplexScenario() {
        val myQueue = MyQueue<Int>()
        myQueue.offer(1)
        myQueue.offer(2)
        assertEquals(1, myQueue.peek())
        assertEquals(1, myQueue.remove())
        assertEquals(2, myQueue.peek())
        assertEquals(2, myQueue.remove())
    }


    @Test
    fun testEmptyQueuePollIsNull() {
        val myQueue = MyQueue<Int>()
        assertEquals(null, myQueue.poll())
    }

    @Test
    fun testPollComplexScenario() {
        val myQueue = MyQueue<Int>()
        myQueue.offer(1)
        myQueue.offer(2)
        myQueue.offer(3)
        assertEquals(1, myQueue.poll())
        assertEquals(2, myQueue.poll())
        assertEquals(3, myQueue.poll())
    }

    @Test
    fun testInitializer() {
        val qData = listOf(1, 2, 3, 4, 5)
        val q = myQueueOf(1, 2, 3, 4, 5)
        qData.forEach { elem -> assertEquals(elem,  q.remove()) }
    }
}
