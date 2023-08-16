package com.techno.authtest.controller

import com.techno.authtest.domain.dto.request.ReqBrandDto
import com.techno.authtest.domain.dto.response.ResBaseDto
import com.techno.authtest.domain.dto.response.ResBrandDto
import com.techno.authtest.service.BrandService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/apiservice/unit")
class BrandController(
    private val brandService: BrandService
) {
    @GetMapping("/getbrand")
    fun getAll(): ResponseEntity<ResBaseDto<ArrayList<ResBrandDto>>> {
        val response = brandService.getAll()
        return ResponseEntity.ok().body(response)
    }
    @PostMapping("/getbrand")
    fun findByDESC_BRAND(@Valid @RequestBody reqBrandDto: ReqBrandDto): ResponseEntity<out ResBaseDto<out Any>> {
        val response = brandService.findByDescBrand(reqBrandDto.reqFilterUnitBrandDto.descBrand)
        return ResponseEntity.ok().body(response)
    }
}