package ru.tinkoff.fintech.lesson4

class MyQueue<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    fun offer(elem: T): Boolean {
        val new = Node(elem)
        if (head == null) {
            head = new
        } else {
            tail!!.next = new
        }

        tail = new
        return true
    }

    fun remove(): T {
        val ans = element()
        doDequeue()
        return ans
    }

    fun poll(): T? {
        val ans = peek()
        if (ans != null) {
            doDequeue()
        }
        return ans
    }

    fun doDequeue() {
        val nextNode = head?.next
        if (nextNode == null) {
            head = null
            tail = null
        } else {
            head = nextNode
        }
    }

    fun peek(): T? {
        return head?.elem
    }

    fun element(): T {
        return head?.elem ?: throw NoSuchElementException("No elements in the queue")
    }
}

fun <T> myQueueOf(vararg data: T): MyQueue<T> {
    val q = MyQueue<T>()
    data.forEach(q::offer)
    return q
} 
