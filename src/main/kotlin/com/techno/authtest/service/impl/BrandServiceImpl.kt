package com.techno.authtest.service.impl

import com.techno.authtest.domain.dto.response.ResBaseDto
import com.techno.authtest.domain.dto.response.ResBrandDto
import com.techno.authtest.domain.entity.BrandEntity
import com.techno.authtest.exception.CustomExceptionHandler
import com.techno.authtest.repository.BrandRepository
import com.techno.authtest.service.BrandService
import org.springframework.stereotype.Service

@Service
class BrandServiceImpl(
    private val brandRepository: BrandRepository
) : BrandService {
    override fun getAll(): ResBaseDto<ArrayList<ResBrandDto>> {
        val data : MutableList<BrandEntity> = brandRepository.findAll()
        if (data.isEmpty())
            throw CustomExceptionHandler("Data Not Found")
        val res: ArrayList<ResBrandDto> = ArrayList()
        data.forEach {
            res.add(
                ResBrandDto(
                    CD_BRAND = it.CD_BRAND!!,
                    DESC_BRAND = it.DESC_BRAND!!
                )
            )
        }
        return ResBaseDto("T", "Success", res)
    }

    override fun findByDescBrand(descBrand: String): ResBaseDto<out Any> {
        val brandEntities = brandRepository.findAllByDescBrand(descBrand)
        if (brandEntities.isNotEmpty()) {
            val brandEntity = brandEntities.first()
            val resBrandDto = ResBrandDto(
                CD_BRAND = brandEntity.CD_BRAND!!,
                DESC_BRAND = brandEntity.DESC_BRAND!!
            )
            return ResBaseDto("T", "Success", resBrandDto)
        }
        if (descBrand.length <= 10 && descBrand.matches(Regex("^[a-zA-Z0-9]*$"))) {
            return getAll()
        }
        else{
            throw CustomExceptionHandler("Invalid Input")
        }
    }
}