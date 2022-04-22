package ru.tinkoff.homework.fintech.lesson9.configuration.client

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("some-client")
class SomeClientProperties(
    val url: String,
    val getCoverByName: String
)