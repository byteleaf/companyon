package de.byteleaf.companyon.user.access

import de.byteleaf.companyon.auth.annotation.IsAdmin
import de.byteleaf.companyon.auth.permission.handler.CurrentUserOrAdminPermission
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.UserUpdate
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.logic.UserService
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserAccessService {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var currentUserOrAdminPermission: CurrentUserOrAdminPermission

    @IsAdmin
    fun findAll(): List<User> = userService.findAll()

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #id)")
    fun get(id: String): User = userService.get(id)

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #id)")
    fun getNullable(id: String?): Optional<User> = userService.getNullable(id)

    @IsAdmin
    fun create(input: UserInput): User = userService.create(input)

    @IsAdmin
    fun update(id: String, input: UserInput): User = userService.update(id, input)

    @IsAdmin
    fun delete(id: String): User = userService.delete(id)

    // TODO try to subscribe without login -> anthony test?
    fun getPublisher(): Publisher<UserUpdate> = userService.getPublisher().filter {
        // TODO check if working
        currentUserOrAdminPermission.hasPermission(it.entity!!.id)
    }
}