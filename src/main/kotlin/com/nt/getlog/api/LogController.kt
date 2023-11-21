package com.nt.getlog.api

import com.nt.getlog.common.CommonResponse
import com.nt.getlog.domain.LogService
import com.nt.getlog.dto.CommonLogResponse
import com.nt.getlog.dto.CommonRequest
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/log")
class LogController(
    val logService: LogService
) {
    @GetMapping("/{id}")
    fun getIdForRequest(
        @PathVariable id : Long,
        request : HttpServletRequest
    ) : CommonResponse<*> {
        val response = logService.makeSomeResponse<CommonRequest.ExceptionLogRequest, CommonLogResponse.ExceptionLogResponse>(
            CommonRequest.ExceptionLogRequest(id, request.getHeader("host"), request.remoteAddr)
        )

        return CommonResponse.success(response)
    }
}