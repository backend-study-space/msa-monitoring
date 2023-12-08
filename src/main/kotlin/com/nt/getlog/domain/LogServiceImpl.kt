package com.nt.getlog.domain

import com.nt.getlog.dto.CommonRequest
import org.springframework.stereotype.Service


@Service
class LogServiceImpl(
    private val logServiceClient: LogServiceClient
) : LogService {
    override fun <T : CommonRequest> makeSomeResponse(request: T): String {
        return logServiceClient.post()
    }
}