package ru.tinkoff.homework.lesson5

data class Car(
    val name: String,
    val brand: Brand,
    val typeBody: BodyType,
    val dollarPrice: Double,
    val gasMileage: Double,
)

enum class Brand {
    BMW, NISSAN, KIA, LAMBORGINI, MINI, RENAULT
}

enum class BodyType {
    SEDAN, SPORTCAR, SUV, HATCHBACK
}