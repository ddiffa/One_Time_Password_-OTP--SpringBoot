package com.codingwithdiffa.server_otp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
class ServerOtpApplication


fun main(args: Array<String>) {
    runApplication<ServerOtpApplication>(*args)
}