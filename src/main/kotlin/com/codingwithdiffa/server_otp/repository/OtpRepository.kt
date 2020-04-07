package com.codingwithdiffa.server_otp.repository

import com.codingwithdiffa.server_otp.entity.Otp
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OtpRepository : CrudRepository<Otp, Long> {

    fun findByCode(code: String): Otp?
}