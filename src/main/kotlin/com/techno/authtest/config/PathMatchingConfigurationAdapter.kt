package com.techno.authtest.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class PathMatchingConfigurationAdapter(
    private val apiAuthInterceptor: ApiAuthInterceptor,
    private val brandInterceptor: BrandInterceptor

) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(apiAuthInterceptor).addPathPatterns("/apiservice/oauth/token")
        registry.addInterceptor(brandInterceptor).addPathPatterns("/apiservice/unit/getbrand")
    }
}