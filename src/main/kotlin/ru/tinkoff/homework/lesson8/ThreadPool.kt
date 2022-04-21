package ru.tinkoff.homework.lesson8

import java.lang.Thread.currentThread
import java.lang.Thread.sleep
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue

class ThreadPool(threadNum: Int) : Executor {

    private val queue: Queue<Runnable> = LinkedBlockingQueue()
    private val threads: MutableList<WorkerThread> = mutableListOf()

    @Volatile
    private var isActive = true

    init {
        if (threadNum <= 0) {
            throw Exception("Thread number should be positive")
        }

        for (i in 1..threadNum) {
            val thread = WorkerThread()
            threads.add(thread)
            threads.last().start()
        }
    }

    override fun execute(task: Runnable) {
        if (!isActive) {
            throw Exception("Thread pool is not active at this moment. Cannot execute task")
        }
        synchronized(queue) {
            queue.add(task)
            (queue as Object).notifyAll()
        }
    }

    fun shutdown() {
        if (isActive) {
            isActive = false
        }
        synchronized(queue) {
            (queue as Object).notifyAll()
        }
    }


    private inner class WorkerThread : Thread() {
        override fun run() {
            while (queue.size > 0 || isActive) {
                var exec: Runnable?;
                synchronized(queue) {
                    while (isActive && queue.size == 0) {
                        (queue as Object).wait()
                    }
                    exec = queue.poll()
                }
                exec?.let {
                    // execute this block if not null
                    exec?.run()
                } ?: run {
                    // execute this block if null
                }

            }
        }
    }

}

fun main() {
    val samplePool = ThreadPool(3)
    val squares = mutableListOf(0, 0, 0)
    for (i in 0 until squares.size) {
        samplePool.execute {
            println("Thread is awake: ${currentThread()}")
            sleep(100)
            squares[i] = i * i
            println(squares[i])
            println("Thread is down: ${currentThread()}")
        }
    }
    samplePool.shutdown()
    sleep(1000)
    println(squares)
}