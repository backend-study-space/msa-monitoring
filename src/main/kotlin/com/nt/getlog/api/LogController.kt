package com.nt.getlog.api

import com.nt.getlog.domain.LogService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/log")
class LogController(
    val logService: LogService
) {
    @GetMapping("/{id}")
    fun getResponse(
        @PathVariable("id") id : Int
    ) : ResponseEntity<*> {
        return ResponseEntity(logService.requestToServer(id),HttpStatus.OK)
    }
}