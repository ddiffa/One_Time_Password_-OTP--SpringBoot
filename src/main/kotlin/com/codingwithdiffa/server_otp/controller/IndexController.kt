package com.codingwithdiffa.server_otp.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {
    @GetMapping("/")
    fun index(): ResponseEntity<Map<String, Any>> {
        val responseData = HashMap<String, Any>()
        responseData["time_server"] =
                System.currentTimeMillis()
        return ResponseEntity(responseData, HttpStatus.OK)
    }
}