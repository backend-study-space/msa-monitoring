package com.nt.getlog.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder

@Configuration
class WebClientConfig(@Value("\${base.uri.host}") val host: String,
                      @Value("\${base.uri.path}") val path: String
) {
    @Bean
    fun webClient() : WebClient {
        val baseURI = UriComponentsBuilder.newInstance().scheme("https")
            .host(host)
            .path(path)
            .build()
            .encode()
            .toUriString()

        return WebClient.create(baseURI)
    }
}