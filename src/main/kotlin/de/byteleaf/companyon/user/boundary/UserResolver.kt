package de.byteleaf.companyon.user.boundary

import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.UserUpdate
import de.byteleaf.companyon.user.dto.input.UserInput
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Controller

@Controller
class UserResolver : GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {

    @Autowired
    private lateinit var userService: UserService

    @Secured("ROLE_ADMIN")
    fun getUsers(): List<User> = userService.findAll()

    @Secured("ROLE_ADMIN")
    fun getUser(id: String): User = userService.get(id)

    @Secured("ROLE_ADMIN")
    fun createUser(input: UserInput): User = userService.create(input)

    @Secured("ROLE_ADMIN")
    fun updateUser(id: String, input: UserInput): User = userService.update(id, input)

    @Secured("ROLE_ADMIN")
    fun deleteUser(id: String): User = userService.delete(id)

    fun userUpdate(): Publisher<UserUpdate> = userService.getPublisher()
}
