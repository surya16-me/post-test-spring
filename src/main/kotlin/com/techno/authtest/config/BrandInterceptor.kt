package com.techno.authtest.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.techno.authtest.domain.dto.response.ResBaseDto
import com.techno.authtest.exception.CustomExceptionHandler
import com.techno.authtest.util.JWTGenerator
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class BrandInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val apiKey = request.getHeader("APIKey")
        val authorization = request.getHeader("Authorization")
        if (apiKey != "123-456-789" || authorization.isNullOrEmpty() || !authorization.startsWith("Bearer "))
            throw CustomExceptionHandler("You dont have permission to access the API")
        val token = authorization.substring(7)
        val isValidToken = validateAndDecodeJWT(token)
        if (!isValidToken){
            val result = ResBaseDto("F", "Signature Not Valid", "")
            val jsonResponse = convertObjectToJson(result)
            response.status = HttpServletResponse.SC_FORBIDDEN
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.writer.write(jsonResponse)
            return false
        }
        return true
    }
    fun convertObjectToJson(resBaseDto: ResBaseDto<*>): String {
        return ObjectMapper().writeValueAsString(resBaseDto)
    }
    fun validateAndDecodeJWT(token: String): Boolean {
        try {

            // Use your JWT library to validate and decode the token
            val decodedToken = JWTGenerator().decodeJWT(token)

            // Check expiration time
            val expirationTime = decodedToken.expiration.time
            val currentTime = System.currentTimeMillis()
            return expirationTime > currentTime

            // You can also perform additional checks here if needed

        } catch (e: Exception) {
            return false
        }
    }
}