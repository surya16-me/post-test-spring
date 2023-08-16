package com.techno.authtest.controller
import com.techno.authtest.domain.dto.request.ReqAuthTokenDto
import com.techno.authtest.domain.dto.response.ResBaseDto
import com.techno.authtest.service.ApiAuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/apiservice/oauth")
class ApiAuthController(
    private val apiAuthService: ApiAuthService
) {
    @PostMapping("/token")
    @ResponseBody
    fun authApi(reqAuthTokenDto: ReqAuthTokenDto): ResponseEntity<ResBaseDto<Any>> {
        val response = apiAuthService.auth(reqAuthTokenDto)
        return ResponseEntity.ok().body(response)
    }
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ResBaseDto<Map<String, String?>>> {
        val errorResponse = ResBaseDto(
            outStat = "F",
            outMess = "Invalid_client",
            outData = mapOf("error_description" to ex.message)
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

}