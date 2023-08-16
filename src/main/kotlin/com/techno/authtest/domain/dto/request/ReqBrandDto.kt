package com.techno.authtest.domain.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class ReqBrandDto(
    @JsonProperty("getListFilterUnitBrand")
    val reqFilterUnitBrandDto: ReqFilterUnitBrandDto
)
