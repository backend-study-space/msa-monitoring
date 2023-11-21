package com.nt.getlog.dto

sealed class CommonRequest(
    open val id : Long = 0,
    open val ip : String = "",
    open val host : String = "",
    open val message : String = "",
    open val exception : String = ""
) {
    data class DefaultLogRequest(
        override val id: Long,
        override val host: String,
        override val ip: String,
    ) : CommonRequest(id, host, ip)

    data class ExceptionLogRequest(
        override val id: Long,
        override val host: String,
        override val ip: String,
        override val message: String = "",
        override val exception: String = "",
    ) : CommonRequest(id, ip, host, message, exception)
}

