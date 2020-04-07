package com.codingwithdiffa.server_otp.remote

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

enum class TextLocal {
    INSTANCE;

    fun getResponseFromTextLocal(codeOtp: String, phoneNumber: String): Response {
        val formRequestBody = FormBody.Builder()
                .add("apiKey", "API_KEY")
                .add("message", "secret code, don't spread it $codeOtp")
                .add("sender", "System")
                .add("numbers", phoneNumber)
                .build()

        val request = Request.Builder()
                .url("https://api.txtlocal.com/send/")
                .post(formRequestBody)
                .build()

        val call = OkHttpClient().newCall(request)
        return call.execute()
    }
}