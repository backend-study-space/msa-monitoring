package com.nt.getlog.domain

import com.nt.getlog.dto.CommonLogResponse
import com.nt.getlog.dto.CommonRequest

interface LogService {
    fun <T : CommonRequest> makeSomeResponse(request: T): CommonLogResponse
}