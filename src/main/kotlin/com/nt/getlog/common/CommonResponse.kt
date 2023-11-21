package com.nt.getlog.common

class CommonResponse<T> (
    val result: Result,
    val data: T,
    val message: String?,
    val error: String?
) {
    enum class Result {
        SUCCESS,
        FAIL,
    }

    companion object {
        fun <T> success(data: T, message: String?): CommonResponse<T> = CommonResponse(Result.SUCCESS, data, message, null)
        fun <T> success(data: T): CommonResponse<T> = success(data, null)
        fun fail(message: String?, errorCode: String?): CommonResponse<*> = CommonResponse(Result.FAIL, null, message, errorCode)
        fun fail(errorCode: ErrorCode): CommonResponse<*> = CommonResponse(Result.FAIL, null, errorCode.errorMessage, errorCode.name)
        fun <T> fail(data: T, message: String?, errorCode: String?): CommonResponse<T> = CommonResponse(Result.FAIL, data, message, errorCode)
    }
}
