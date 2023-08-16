package com.techno.authtest.repository

import com.techno.authtest.domain.entity.BrandEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface BrandRepository : JpaRepository<BrandEntity, String> {
    @Transactional
    @Query("SELECT b FROM BrandEntity b WHERE b.DESC_BRAND = :descBrand")
    fun findAllByDescBrand(descBrand: String): List<BrandEntity>
}