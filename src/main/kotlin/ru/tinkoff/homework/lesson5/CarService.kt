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
    private const val dollarToYuan = 6.37

    private fun getCarLocalized(
        car: Car,
        bodyType: String,
        language: Language,
        currency: Currency
    ): CarLocalized {
        val currencyRatio = when (currency) {
            Currency.YUAN -> dollarToYuan;
            Currency.EURO -> dollarToEuro;
            else -> {
                dollarToEuro
            }
        }
        val price = car.dollarPrice * currencyRatio
        return CarLocalized(
            language,
            currency,
            car.brand.toString(),
            car.name,
            bodyType,
            price,
            car.gasMileage,
        )
    }

    private fun toEng(car: Car): CarLocalized {
        var bodyType: String = when (car.typeBody) {
            BodyType.SEDAN -> "Sedan"
            BodyType.SPORTCAR -> "Sportcar"
            BodyType.HATCHBACK -> "Hatchback"
            BodyType.SUV -> "SUV"
        }
        return getCarLocalized(car, bodyType, Language.ENG, Currency.EURO)
    }

    private fun toZh(car: Car): CarLocalized {
        var bodyType: String = when (car.typeBody) {
            BodyType.SEDAN -> "轿车"
            BodyType.SPORTCAR -> "跑车"
            BodyType.HATCHBACK -> "掀背车"
            BodyType.SUV -> "越野车"
        }
        return getCarLocalized(car, bodyType, Language.ZH, Currency.YUAN)
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