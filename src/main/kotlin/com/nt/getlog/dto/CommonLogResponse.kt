package com.nt.getlog.dto

sealed class CommonLogResponse(
    open val request : CommonRequest,
    open val data: Map<String, Any>
) {
    data class ExceptionLogResponse(
        override val request: CommonRequest,
        override val data: Map<String, Any>,
        val message: String = "정상 요청 완료",
    ) : CommonLogResponse(request, data)
}