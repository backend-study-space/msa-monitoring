package com.nt.getlog.common

enum class ErrorCode(val errorMessage: String) {
    COMMON_SYSTEM_ERROR("일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
    COMMON_INVALID_PARAMETER("잘못된 요청입니다."),
    COMMON_NOT_AUTHORIZED("권한이 없습니다."),
}
