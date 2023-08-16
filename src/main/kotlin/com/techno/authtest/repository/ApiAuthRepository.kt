package com.techno.authtest.repository

import com.techno.authtest.domain.entity.CredentialEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ApiAuthRepository : JpaRepository<CredentialEntity, String> {
    fun findAuthByGrantType(grantType: String?): CredentialEntity?
}