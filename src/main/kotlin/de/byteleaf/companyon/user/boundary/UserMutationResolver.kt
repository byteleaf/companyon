package de.byteleaf.companyon.user.boundary

import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.input.UserInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
//
//@Controller
//class UserMutationResolver : GraphQLMutationResolver {
//
//    @Autowired
//    private lateinit var userService: UserService
//
//    fun updateUser(id: String, input: UserInput): User = userService.update(id, input)
//
//    fun createUser(input: UserInput): User = userService.create(input)
//
//    fun deleteUser(id: String): Boolean {
//        userService.delete(id)
//        return true
//    }
//}
