package com.techno.authtest.service

import com.techno.authtest.domain.dto.response.ResBaseDto
import com.techno.authtest.domain.dto.response.ResBrandDto

interface BrandService {
    fun getAll(): ResBaseDto<ArrayList<ResBrandDto>>
    fun findByDescBrand(descBrand: String): ResBaseDto<out Any>
}