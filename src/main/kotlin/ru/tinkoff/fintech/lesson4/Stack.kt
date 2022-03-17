package ru.tinkoff.fintech.lesson4

class MyStack<T> {
    private var head: Node<T>? = null

    fun push(elem: T) {
        val new = Node(elem)
        if (head == null) {
            head = new
        } else {
            new.next = head
            head = new
        }
    }

    fun pop(): T {
        val ans = peek()
        head = head?.next
        return ans
    }

    fun peek(): T = head?.elem ?: throw NoSuchElementException("No element on the stack")
}

fun <T> myStackOf(vararg data: T): MyStack<T> {
    val s = MyStack<T>()
    data.forEach(s::push)
    return s
}
