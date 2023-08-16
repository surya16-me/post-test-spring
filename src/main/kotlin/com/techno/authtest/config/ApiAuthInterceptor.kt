package com.techno.authtest.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.techno.authtest.domain.dto.response.ResBaseDto
import com.techno.authtest.service.impl.ApiAuthServiceImpl
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.awt.PageAttributes
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ApiAuthInterceptor(
    private val apiAuthServiceImpl: ApiAuthServiceImpl
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val apiKey = request.getHeader("API-Key")
        val contentType = request.getHeader(HttpHeaders.CONTENT_TYPE)
        println(apiKey)
        println(contentType)
        if(apiKey != "123-456-789" || contentType!="application/x-www-form-urlencoded"){
            val result = ResBaseDto("F", "Failed", "Error")
            val jsonResponse = convertObjectToJson(result)

            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.status = HttpServletResponse.SC_UNAUTHORIZED

            response.writer.write(jsonResponse)
            return false
        }

        return true
    }

    fun convertObjectToJson(resBaseDto: ResBaseDto<*>): String {
        return ObjectMapper().writeValueAsString(resBaseDto)
    }
}