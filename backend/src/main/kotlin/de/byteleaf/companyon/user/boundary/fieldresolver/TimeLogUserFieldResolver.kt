package de.byteleaf.companyon.user.boundary.fieldresolver

import de.byteleaf.companyon.timelog.dto.TimeLog
import de.byteleaf.companyon.user.access.UserAccessService
import de.byteleaf.companyon.user.dto.User
import graphql.kickstart.tools.GraphQLResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class TimeLogUserFieldResolver : GraphQLResolver<TimeLog> {

    @Autowired
    private lateinit var userAccessService: UserAccessService

    fun getUser(timeLog: TimeLog): Optional<User> = userAccessService.getNullable(timeLog.user)
}
