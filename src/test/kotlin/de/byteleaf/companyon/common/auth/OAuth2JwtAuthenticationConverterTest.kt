package de.byteleaf.companyon.common.auth

import de.byteleaf.companyon.user.control.UserService
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner
import kotlin.reflect.KClass


@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [OAuth2JwtAuthenticationConverter::class, UserService::class])
@DataMongoTest
class OAuth2JwtAuthenticationConverterTest {

    @Autowired
    private lateinit var converter: OAuth2JwtAuthenticationConverter

    // TODO SIT
    @Test
    fun convert() {
        val a = 9;

    }
}