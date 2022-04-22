package ru.tinkoff.homework.lesson9.configuration.client

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import reactor.netty.http.client.HttpClient

@Service
class SomeClient(
    @Qualifier("SomeClient") private val client: WebClient,
    private val someClient: SomeClientProperties
) {
    suspend fun getCoverByName(songName: String): ByteArray = client.get()
        .uri { uriBuilder ->
            uriBuilder
                .path(someClient.getCoverByName)
                .build(songName)
        }
        .accept(MediaType.IMAGE_JPEG)
        .retrieve()
        .awaitBody()
}
