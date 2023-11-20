package com.nt.getlog.dto

data class LogResponse(
    val host: String,
    val method: String,
    val value: Map<String, Any>?
)