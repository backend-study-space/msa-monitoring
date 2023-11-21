package com.nt.getlog.domain

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nt.getlog.dto.CommonLogResponse
import com.nt.getlog.dto.CommonRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.UriComponentsBuilder

@Service
class LogServiceImpl(
    val objectMapper : ObjectMapper,
    @Value("\${base.uri.host}") val host: String,
    @Value("\${base.uri.path}") val path: String
) : LogService {

    private val baseURI = UriComponentsBuilder.newInstance().scheme("https")
        .host(host)
        .path(path)
        .build()
        .encode()
        .toUri()

    override fun <T : CommonRequest, R : CommonLogResponse> makeSomeResponse(request: T): CommonLogResponse {
        val uri = UriComponentsBuilder.fromUri(baseURI)
            .path("/{id}")
            .build()
            .expand(request.id)
            .encode()
            .toUri()

        return WebClient.create()
            .get()
            .uri(uri)
            .retrieve()
            .bodyToMono<String>()
            .block()
            .let { objectMapper.readValue<Map<String, Any>>(it!!)
                .let {map -> if (map["message"] != null) {
                        CommonLogResponse.ExceptionLogResponse(request, map, map["message"].toString())
                    } else {
                        CommonLogResponse.ExceptionLogResponse(request, map)
                    }
                }
            }
    }
}