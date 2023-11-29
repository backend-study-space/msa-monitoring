package com.nt.getlog.domain

import com.fasterxml.jackson.databind.ObjectMapper
import com.nt.getlog.domain.TestUrlArgument.Companion.TEST_RESPONSE_EXIST_MESSAGE
import com.nt.getlog.domain.TestUrlArgument.Companion.TEST_RESPONSE_NOT_EXIST_MESSAGE
import com.nt.getlog.dto.CommonRequest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

class LogServiceImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var webClient: WebClient
    private lateinit var objectMapper: ObjectMapper
    private lateinit var logService: LogService

    @BeforeEach
    fun setup() {
        objectMapper = ObjectMapper()

        mockWebServer = MockWebServer()
        mockWebServer.start()

        webClient = WebClient.builder().baseUrl(mockWebServer.url("").toString())

            .defaultHeaders { headers ->
                headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            }
            .codecs { configurer ->
                configurer.defaultCodecs().maxInMemorySize(5 * 1024 * 1024)
            }
            .build()

        logService = LogServiceImpl(webClient, objectMapper)
    }

    @AfterEach
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    @DisplayName("message_필드가_존재")
    fun makeSomeResponseExistMessage() {
        mockWebServer.enqueue(MockResponse().setBody(TEST_RESPONSE_EXIST_MESSAGE))

        val response = logService.makeSomeResponse(CommonRequest.DefaultLogRequest(1, "host", "0.0.0.0"))

        assertEquals(objectMapper.writeValueAsString(response.data), TEST_RESPONSE_EXIST_MESSAGE)
        assertNotNull(response.data["message"])
    }

    @Test
    @DisplayName("message_필드가_존재하지_않음")
    fun makeSomeResponseNotExistMessage() {
        mockWebServer.enqueue(MockResponse().setBody(TEST_RESPONSE_NOT_EXIST_MESSAGE))

        val response = logService.makeSomeResponse(CommonRequest.DefaultLogRequest(1, "host", "0.0.0.0"))

        assertEquals(objectMapper.writeValueAsString(response.data), TEST_RESPONSE_NOT_EXIST_MESSAGE)

        assertNull(response.data["message"])
    }

}