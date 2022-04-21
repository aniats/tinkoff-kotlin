package ru.tinkoff.homework.fintech.lesson8

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe

class ThreadPoolTest: FeatureSpec() {
    init {
        feature("Correct behaviour test") {
            scenario("Calculating Squares in mutableList") {
                val samplePool = ThreadPool(3)
                val squares = mutableListOf(0, 0, 0)
                for (i in 0 until squares.size) {
                    samplePool.execute {
                        squares[i] = i * i
                    }
                }
                samplePool.shutdown()
                squares shouldBe mutableListOf(0, 1, 4)
            }
        }
        feature("Testing corner cases behaviour") {
            scenario("Executing stopped ThreadPool") {
                shouldThrow<Exception> {
                    val pool = ThreadPool(1)
                    pool.shutdown()
                    pool.execute { 1 + 1 }
                }
            }
            scenario("Incorrect threads number") {
                shouldThrow<Exception> { ThreadPool(-1) }
            }
        }
    }
}