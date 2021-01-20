package de.byteleaf.companyon.user.access

import de.byteleaf.companyon.auth.annotation.IsAdmin
import de.byteleaf.companyon.user.logic.UserService
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.UserUpdate
import de.byteleaf.companyon.user.dto.input.UserInput
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserAccessService {

    @Autowired
    private lateinit var userService: UserService

    @IsAdmin
    fun findAll(): List<User> = userService.findAll()

    @IsAdmin
    fun get(id: String): User = userService.get(id)

    @IsAdmin
    fun create(input: UserInput): User = userService.create(input)

    @IsAdmin
    fun update(id: String, input: UserInput): User = userService.update(id, input)

    @IsAdmin
    fun delete(id: String): User = userService.delete(id)

    fun getPublisher(): Publisher<UserUpdate> = userService.getPublisher()
}