package de.byteleaf.companyon.security.control

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service

@Service
class AuthInfoService {

    @Value("\${spring.security.oauth2.resource.user-info-uri}")
    private lateinit var userInfoUri: String

    fun loadUserInfo(tokenValue: String): Map<String, String> {
        val restTemplate = RestTemplateBuilder()
            .defaultHeader("Authorization", "Bearer $tokenValue")
            .build()

        @Suppress("UNCHECKED_CAST")
        return restTemplate.getForEntity(userInfoUri, Map::class.java).body as Map<String, String>?
            ?: throw Exception("Can't load user meta data from")
    }
}