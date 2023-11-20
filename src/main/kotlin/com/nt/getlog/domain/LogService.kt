package com.nt.getlog.domain

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nt.getlog.dto.LogResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono


@Service
class LogService(
    val objectMapper : ObjectMapper
) {

    @Value("\${base-url}")
    private lateinit var baseUrl: String

    @Value("\${uri-path}")
    private lateinit var uriPath: String

    fun requestToServer(id : Int) : LogResponse? {
        val webClient = WebClient
            .builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()

        val response = webClient
            .get()
            .uri {
                it.path(uriPath)
                    .queryParam("id", id)
                    .build() }
            .retrieve()
            .bodyToMono<String>()
            .block().let { objectMapper.readValue<Map<String, Any>>(it!!) }

        return LogResponse("", "", response)
    }
}
