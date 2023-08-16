package com.techno.authtest.exception

import com.techno.authtest.domain.dto.response.ResBaseDto
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerArgumentNotValidException(exception:
                                         MethodArgumentNotValidException): ResponseEntity<Any> {
        val errors = mutableListOf<String>()
        exception.bindingResult.fieldErrors.forEach {
            errors.add(it.defaultMessage!!)
        }
        val response = (ResBaseDto("F", "Something Went Wrong",null))
        return ResponseEntity.badRequest().body(response)
    }
    @ExceptionHandler(CustomExceptionHandler::class)
    fun handleCustomException(exception: RuntimeException): ResponseEntity<Any>{
        val response = ResBaseDto("F",exception.message!!, null)
        return ResponseEntity.badRequest().body(response)
    }
    @ExceptionHandler(Exception::class)
    fun handlerException(exception: Exception): ResponseEntity<ResBaseDto<String>> {
        println("Error General!!")
        exception.printStackTrace()
        val response = ResBaseDto("F", "invalid_client" ,exception.message!!)
        return ResponseEntity.badRequest().body(response)
    }
}