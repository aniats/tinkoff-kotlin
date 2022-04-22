package ru.tinkoff.homework.fintech.lesson9.configuration

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import ru.tinkoff.homework.fintech.lesson9.configuration.client.SomeClientProperties

@Configuration
class SomeServiceConfiguration(private val someClient: SomeClientProperties) {
    @Bean
    @Qualifier("SomeWebClient")
    fun someWebClient(webClientBuilder: WebClient.Builder): WebClient =
        webClientBuilder
            .clientConnector(ReactorClientHttpConnector(
                HttpClient.create().followRedirect(true)
            ))
            .baseUrl(someClient.url).build()
}

