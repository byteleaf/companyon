package de.byteleaf.companyon.test.mock

import de.byteleaf.companyon.auth.logic.SecurityContextService
import de.byteleaf.companyon.user.dto.output.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary

@Primary
class SecurityContextServiceMock : SecurityContextService() {

    @Autowired
    private lateinit var securityContextMock: SecurityContextMock

    override fun getCurrentUser(): User {
        return securityContextMock.getUser()
    }
}
