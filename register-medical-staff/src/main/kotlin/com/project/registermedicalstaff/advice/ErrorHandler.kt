package com.project.registermedicalstaff.advice

import com.fasterxml.jackson.core.JsonParseException
import com.project.registermedicalstaff.responses.ResponseErrorHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandler {
    @ExceptionHandler(JsonParseException::class)
    fun JsonFormatExceptionHandler(serveletRequest: HttpServletRequest, serveletResponse: HttpServletResponse, exception: Exception): ResponseEntity<ResponseErrorHandler> {
        return ResponseEntity(ResponseErrorHandler("JSON ERROR", exception.message ?: "invalid json"), HttpStatus.BAD_REQUEST)
    }
}
