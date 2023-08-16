package com.techno.authtest.domain.entity

import javax.persistence.*

@Entity
@Table(name = "credentialauth")
data class CredentialEntity(


    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "id", columnDefinition = "bigint")
    val id: Long? = null,

    @field:Column(name = "grantType", columnDefinition = "varchar")
    val grantType : String? = null,
)
