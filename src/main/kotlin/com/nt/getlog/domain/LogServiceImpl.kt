package com.nt.getlog.domain

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nt.getlog.common.ErrorCode
import com.nt.getlog.dto.CommonLogResponse
import com.nt.getlog.dto.CommonRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import java.lang.IllegalArgumentException

@Service
class LogServiceImpl(
    val webClient: WebClient,
    val objectMapper : ObjectMapper
) : LogService {
    override fun <T : CommonRequest> makeSomeResponse(request: T): CommonLogResponse {

        return webClient
            .get()
            .uri(request.id.toString())
            .retrieve()
            .bodyToMono<String>()
            .flatMap { responseBody ->
                val map = objectMapper.readValue<Map<String, Any>>(responseBody)
                if (map["message"] != null) {
                    Mono.just(CommonLogResponse.ExceptionLogResponse(request, map, map["message"].toString()))
                } else {
                    Mono.just(CommonLogResponse.ExceptionLogResponse(request, map))
                }
            }
            .onErrorMap { e ->
                IllegalArgumentException(ErrorCode.COMMON_SYSTEM_ERROR.errorMessage, e)
            }.block()!!
    }
}