package com.nt.getlog.domain

import com.nt.getlog.dto.CommonLogResponse
import com.nt.getlog.dto.CommonRequest

interface LogService {
    fun <T : CommonRequest, R : CommonLogResponse> makeSomeResponse(request: T): CommonLogResponse
}