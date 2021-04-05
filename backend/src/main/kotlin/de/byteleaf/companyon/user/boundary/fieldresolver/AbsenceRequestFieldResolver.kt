package de.byteleaf.companyon.user.boundary.fieldresolver

import de.byteleaf.companyon.absence.dto.output.AbsenceRequest
import de.byteleaf.companyon.user.access.UserAccessService
import de.byteleaf.companyon.user.dto.User
import graphql.kickstart.tools.GraphQLResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import java.util.*


@Controller
class AbsenceRequestFieldResolver : GraphQLResolver<AbsenceRequest> {

    @Autowired
    private lateinit var userAccessService: UserAccessService

    fun getUser(timeLog: AbsenceRequest): Optional<User> = userAccessService.getNullable(timeLog.user)
}