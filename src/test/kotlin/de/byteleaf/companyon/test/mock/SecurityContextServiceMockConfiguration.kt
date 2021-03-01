package de.byteleaf.companyon.test.mock

import de.byteleaf.companyon.auth.logic.SecurityContextService
import de.byteleaf.companyon.user.dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

//@TestConfiguration
//class SecurityContextServiceMockConfiguration {
//
//    @Autowired
//    private lateinit var securityContextMock: SecurityContextMock
//
//    @Bean
//    @Primary
//    fun securityContextService(): SecurityContextService {
//        return securityContextMock.getUser()
//    }
//
//}