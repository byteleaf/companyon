package de.byteleaf.companyon.user.boundary

import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.User
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class UserResolver : GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {

}

@Autowired
private lateinit var userService: UserService

fun getUsers(): List<User> = userService.findAll()

fun getUser(id: String): User = userService.get(id)

fun getCurrentUser(): User = userService.getCurrentUser()
