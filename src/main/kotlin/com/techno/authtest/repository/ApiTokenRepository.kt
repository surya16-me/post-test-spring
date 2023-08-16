package com.techno.authtest.repository

import com.techno.authtest.domain.entity.TokenEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ApiTokenRepository : JpaRepository<TokenEntity, String> {

}