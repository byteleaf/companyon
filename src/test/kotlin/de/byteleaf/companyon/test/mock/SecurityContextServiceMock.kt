package de.byteleaf.companyon.test.mock

import de.byteleaf.companyon.auth.logic.SecurityContextService
import de.byteleaf.companyon.user.dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Primary
@Component
class SecurityContextServiceMock: SecurityContextService() {

    @Autowired
    private lateinit var securityContextMock: SecurityContextMock

    override fun getCurrentUser(): User {
        return securityContextMock.getUser()
    }

}