package com.techno.authtest.exception

import java.lang.RuntimeException

class CustomExceptionHandler(message: String) : RuntimeException(message) {

}