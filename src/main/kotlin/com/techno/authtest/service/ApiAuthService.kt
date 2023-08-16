package com.techno.authtest.service

import com.techno.authtest.domain.dto.request.ReqAuthTokenDto
import com.techno.authtest.domain.dto.response.ResBaseDto

interface ApiAuthService {
    fun auth(reqAuthTokenDto: ReqAuthTokenDto) : ResBaseDto<Any>
}