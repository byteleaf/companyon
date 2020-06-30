package de.byteleaf.companyon.user.boundary

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.input.UserInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class UserMutationResolver : GraphQLMutationResolver {

    @Autowired
    private lateinit var userService: UserService;

    fun updateUser(id: Long, input: UserInput): User = userService.update(id, input)

    fun createUser(input: UserInput): User = userService.create(input)

    // TODO implement Skalar Void: https://byteleaf.atlassian.net/browse/COM-36
    fun deleteUser(id: Long): Boolean {
        userService.delete(id)
        return true
    }
}
