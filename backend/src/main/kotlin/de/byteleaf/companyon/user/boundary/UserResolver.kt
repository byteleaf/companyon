package de.byteleaf.companyon.user.boundary

import de.byteleaf.companyon.user.access.UserAccessService
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.UserUpdate
import de.byteleaf.companyon.user.dto.input.UserInput
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class UserResolver : GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {

    @Autowired
    private lateinit var userAccessService: UserAccessService

    fun getUsers(): List<User> = userAccessService.findAll()

    fun getUser(id: String): User = userAccessService.get(id)

    fun createUser(input: UserInput): User = userAccessService.create(input)

    fun updateUser(id: String, input: UserInput): User = userAccessService.update(id, input)

    fun deleteUser(id: String): User = userAccessService.delete(id)

    fun userUpdate(): Publisher<UserUpdate> = userAccessService.getPublisher()
}
