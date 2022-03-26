package ru.tinkoff.homework.lesson5

enum class Language {
    RUS, ENG, ZH
}

enum class Currency {
    RUBLE, DOLLAR, YUAN, EURO
}

data class CarLocalized(
    val language: Language,
    val currency: Currency,
    val brand: String,
    val name: String,
    val bodyType: String,
    val price: Double,
    val gasMileage: Double,
)

object CarService {
    private const val dollarToEuro = 0.91

    private fun toEng(car: Car): CarLocalized {
        var bodyType: String = when (car.typeBody) {
            BodyType.SEDAN -> "Sedan"
            BodyType.SPORTCAR -> "Sportcar"
            BodyType.HATCHBACK -> "Hatchback"
            BodyType.SUV -> "SUV"
        }
        return CarLocalized(
            Language.ENG,
            Currency.EURO,
            car.brand.toString(),
            car.name,
            bodyType,
            car.dollarPrice * dollarToEuro,
            car.gasMileage,
        )
    }

    fun description(cars: Collection<Car>): List<CarLocalized> {
        return cars.asSequence().sortedBy { it.dollarPrice }.map { toEng(it) }.toList()
    }

    fun groupByBodyType(cars: Collection<Car>): Map<BodyType, List<Car>> {
        return cars.groupBy { it.typeBody }
    }

    fun findOptions(cars: Collection<Car>, predicate: (Car) -> (Boolean)): List<String> {
        return cars.asSequence().filter { predicate(it) }.map { "${it.brand} ${it.name}" }.take(3).toList()
    }
}