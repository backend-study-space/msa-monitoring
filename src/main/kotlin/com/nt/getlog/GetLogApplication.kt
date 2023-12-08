package com.nt.getlog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class GetLogApplication

fun main(args: Array<String>) {
	runApplication<GetLogApplication>(*args)
}
