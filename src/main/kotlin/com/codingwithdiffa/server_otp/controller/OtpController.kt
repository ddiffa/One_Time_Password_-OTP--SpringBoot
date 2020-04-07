package com.codingwithdiffa.server_otp.controller

import com.codingwithdiffa.server_otp.remote.TextLocal
import com.codingwithdiffa.server_otp.service.OtpService
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/otp")
class OtpController {

    @Autowired
    private lateinit var otpService: OtpService

    @PostMapping("/send")
    fun sendOtp(@RequestAttribute(required = true, name = "phone_number") phoneNumber: String): ResponseEntity<Map<String, Any>> {
        val codeOtp = otpService.createOtp()

        val response = TextLocal.INSTANCE.getResponseFromTextLocal(codeOtp, phoneNumber)
        val responseData = HashMap<String, Any>()

        responseData["time_server"] = System.currentTimeMillis()
        val isSuccessful = response.isSuccessful
        val responseCode = response.code
        val jsonObjectResponseFromServer = JSONObject(response.body?.string())
        val responseStatusMessage = jsonObjectResponseFromServer.getString("status")

        println("response Data From Server : $jsonObjectResponseFromServer")

        if (isSuccessful && responseCode == 200 && responseStatusMessage == "success") {
            responseData["success"] = true
        } else {
            responseData["success"] = false
            otpService.deleteOtp(codeOtp)
        }
        return ResponseEntity(responseData, HttpStatus.OK)

    }

    @PostMapping("/update")
    fun updateOtp(@RequestAttribute(required = true, name = "otp_code") codeOtp: String): ResponseEntity<Map<String, Any>> {
        val responseData = HashMap<String, Any>()
        responseData["time_server"] = System.currentTimeMillis()
        responseData["success"] = otpService.updateOtp(codeOtp)
        return ResponseEntity(responseData, HttpStatus.OK)
    }

}