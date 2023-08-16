package com.techno.authtest.domain.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tokenauth")
data class TokenEntity(

    @field:Id
    @field:Column(name = "uuid", columnDefinition = "uuid")
    val id: UUID? = null,

    @field:Column(name = "access_token", columnDefinition = "varchar")
    val accessToken : String? = null,

    @field:Column(name = "token_type", columnDefinition = "varchar")
    val tokenType : String? = null,

    @field:Column(name = "expires_in", columnDefinition = "bigint")
    val expiresIn : Long? = null,
)
