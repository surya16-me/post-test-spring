package com.techno.authtest.domain.dto.response

import java.time.LocalDateTime

data class ResAuthTokenDto(
    val accessToken: String? = null,
    val tokenType: String? = null,
    val expiresIn: Long? = null,

)
