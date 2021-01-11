package de.byteleaf.companyon.security.boundary

import de.byteleaf.companyon.security.control.SecurityContextService
import de.byteleaf.companyon.user.dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.security.access.prepost.PreAuthorize


@Controller
class SecurityResolver: GraphQLQueryResolver {

        @Autowired
        private lateinit var securityContextService: SecurityContextService

        fun getCurrentUser(): User = securityContextService.getCurrentUser()
}