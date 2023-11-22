package com.nt.getlog.dto

sealed class CommonRequest(
    open val id : Long = 0,
    open val ip : String = "",
    open val host : String = "",
) {
    data class DefaultLogRequest(
        override val id: Long,
        override val host: String,
        override val ip: String,
    ) : CommonRequest(id, host, ip)
}

