package com.techno.authtest.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter

class JWTGenerator {
    companion object{
        private const val SECRET_KEY = "TECHNOSECRET"
        private val instance : JWTGenerator = JWTGenerator()
    }

    fun createJWT(subject: String,ttlMills: Long): String? {
        val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS256
        val nowMills: Long = System.currentTimeMillis()
        val now = Date(nowMills)

        val apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY)
        val signingKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.jcaName)

        val builder : JwtBuilder = Jwts.builder()
            .setSubject(AESUtils.encrypt(subject, SECRET_KEY))
            .setIssuedAt(now)
            .signWith(signatureAlgorithm, signingKey)

        if(ttlMills>=0){
            val expMills = nowMills + ttlMills
            val exp = Date(expMills)
            builder.setExpiration(exp)

        }
        return builder.compact()
    }

    fun decodeJWT(token: String): Claims {
        val claims: Claims = Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .parseClaimsJws(token).body

        return claims
    }

}