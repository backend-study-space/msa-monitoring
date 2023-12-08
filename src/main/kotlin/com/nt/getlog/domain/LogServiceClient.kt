package com.nt.getlog.domain

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(name = "log-client-service", url = "\${props.log-client-service.url}")
interface LogServiceClient {

    @RequestMapping(method = [RequestMethod.POST], value = ["/exception"])
    fun post(): String
}