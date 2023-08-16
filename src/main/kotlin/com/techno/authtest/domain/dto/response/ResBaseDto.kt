package com.techno.authtest.domain.dto.response

data class ResBaseDto<T> (
    val outStat : String = "T",
    val outMess : String = "Success",
    val outData : T? = null
)