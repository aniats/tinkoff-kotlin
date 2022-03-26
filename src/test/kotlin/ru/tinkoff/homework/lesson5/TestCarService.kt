package ru.tinkoff.homework.lesson5

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.math.exp

class TestCarService {
    private val testSample = listOf(
        Car("X-trail", Brand.NISSAN, BodyType.SUV, 45_000.0, 7.2),
        Car("Sportage", Brand.KIA, BodyType.SUV, 35_000.0, 7.9),
        Car("Countach", Brand.LAMBORGINI, BodyType.SPORTCAR, 2_640_000.0, 14.3),
        Car("Cooper", Brand.MINI, BodyType.HATCHBACK, 31_900.0, 7.6),
        Car("Logan", Brand.RENAULT, BodyType.SEDAN, 12_000.0, 7.1),
    )

    @Test
    fun testFindOptions() {
        val expectation = listOf(
            "LAMBORGINI Countach"
        )
        val predicate: (Car) -> Boolean = { it.brand == Brand.LAMBORGINI }
        val result = CarService.findOptions(testSample, predicate)
        assertEquals(expectation, result)
    }

    @Test
    fun testGroupByBodyType() {
        val expectation = mapOf(
            BodyType.SPORTCAR to listOf(
                Car("Countach", Brand.LAMBORGINI, BodyType.SPORTCAR, 2_640_000.0, 14.3),
            ),
            BodyType.SEDAN to listOf(
                Car("Logan", Brand.RENAULT, BodyType.SEDAN, 12_000.0, 7.1),
            ),
            BodyType.SUV to listOf(
                Car("X-trail", Brand.NISSAN, BodyType.SUV, 45_000.0, 7.2),
                Car("Sportage", Brand.KIA, BodyType.SUV, 35_000.0, 7.9),
            ),
            BodyType.HATCHBACK to listOf(
                Car("Cooper", Brand.MINI, BodyType.HATCHBACK, 31_900.0, 7.6),
            )
        )
        val result = CarService.groupByBodyType(testSample)
        assertEquals(result, expectation)
    }

    @Test
    fun testDescription() {
        val expectation = listOf(
            CarLocalized(
                Language.ENG,
                Currency.EURO,
                Brand.RENAULT.toString(),
                "Logan",
                "Sedan",
                12_000 * 0.91,
                7.1
            ),
            CarLocalized(
                Language.ENG,
                Currency.EURO,
                Brand.MINI.toString(),
                "Cooper",
                "Hatchback",
                31_900 * 0.91,
                7.6
            ),
            CarLocalized(
                Language.ENG,
                Currency.EURO,
                Brand.KIA.toString(),
                "Sportage",
                "SUV",
                35_000.0 * 0.91,
                7.9
            ),
            CarLocalized(
                Language.ENG,
                Currency.EURO,
                Brand.NISSAN.toString(),
                "X-trail",
                "SUV",
                45_000 * 0.91,
                7.2
            ),
            CarLocalized(
                Language.ENG,
                Currency.EURO,
                Brand.LAMBORGINI.toString(),
                "Countach",
                "Sportcar",
                2_640_000 * 0.91,
                14.3
            ),
        )
        val result = CarService.description(testSample)
        assertEquals(result, expectation)
    }
}