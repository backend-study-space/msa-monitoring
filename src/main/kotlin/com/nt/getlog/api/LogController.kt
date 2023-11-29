package com.nt.getlog.api

import com.nt.getlog.common.CommonResponse
import com.nt.getlog.domain.LogService
import com.nt.getlog.dto.CommonRequest
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/log")
class LogController(
    val logService: LogService
) {

    var logger: Logger = LoggerFactory.getLogger(LogController::class.java)
    @GetMapping("/{id}")
    fun getIdForRequest(
        @PathVariable id : Long,
        request : HttpServletRequest
    ) : CommonResponse<*> {
        val response = logService.makeSomeResponse(
            CommonRequest.DefaultLogRequest(id, request.getHeader("host"), request.remoteAddr)
        )

        logger.debug("통신")

        return CommonResponse.success(response)
    }
}