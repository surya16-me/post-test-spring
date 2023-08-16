package com.techno.authtest.service.impl

import com.techno.authtest.domain.dto.request.ReqAuthTokenDto
import com.techno.authtest.domain.dto.response.ResAuthTokenDto
import com.techno.authtest.domain.dto.response.ResBaseDto
import com.techno.authtest.domain.entity.TokenEntity
import com.techno.authtest.exception.CustomExceptionHandler
import com.techno.authtest.repository.ApiAuthRepository
import com.techno.authtest.repository.ApiTokenRepository
import com.techno.authtest.service.ApiAuthService
import com.techno.authtest.util.JWTGenerator
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.UUID

@Service
class ApiAuthServiceImpl(
    private val apiAuthRepository: ApiAuthRepository,
    private val apiTokenRepository: ApiTokenRepository,
    private var token: String? = null,
    private var lastGeneratedTime: Long = 0
) : ApiAuthService{
    override fun auth(reqAuthTokenDto: ReqAuthTokenDto): ResBaseDto<Any> {
        val data = apiAuthRepository.findAuthByGrantType(reqAuthTokenDto.grantType) ?: throw CustomExceptionHandler("Credential not found")
        if (reqAuthTokenDto.grantType != data.grantType)
            throw CustomExceptionHandler("Credential not found!!")
        val currentTime = System.currentTimeMillis()
        val expiredJwt = 5000L
        if (token == null || currentTime - lastGeneratedTime >= 5000) {
            token = JWTGenerator().createJWT(
                reqAuthTokenDto.grantType!!,
                expiredJwt
            )
            lastGeneratedTime = currentTime
        }

        val dataEntity = TokenEntity(
            id = UUID.randomUUID(),
            accessToken = token,
            tokenType = "Bearer",
            expiresIn = expiredJwt
        )
        val entity = apiTokenRepository.save(dataEntity)
        val res = ResAuthTokenDto(
            accessToken = entity.accessToken,
            tokenType = entity.tokenType,
            expiresIn = entity.expiresIn,
        )
        return ResBaseDto(outData = res)
    }

}