package de.byteleaf.companyon.user.boundary.fieldresolver

import de.byteleaf.companyon.task.dto.Task
import de.byteleaf.companyon.user.access.UserAccessService
import de.byteleaf.companyon.user.dto.output.User
import graphql.kickstart.tools.GraphQLResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class TaskUserFieldResolver : GraphQLResolver<Task> {

    @Autowired
    private lateinit var userAccessService: UserAccessService

    fun getUser(task: Task): Optional<User> = userAccessService.getNullable(task.user)
}
