package com.nt.getlog.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/log")
class LogController {

    @PostMapping
    fun getLog(@RequestBody log : String?) : ResponseEntity<String> {
        return ResponseEntity("dd", HttpStatus.OK);
    }
}