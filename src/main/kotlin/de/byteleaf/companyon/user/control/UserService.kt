package de.byteleaf.companyon.user.control

import de.byteleaf.companyon.common.control.AbstractDataService
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.entity.UserEntity
import de.byteleaf.companyon.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService : AbstractDataService<UserEntity, User, UserInput, UserRepository>() {

    /**
     * To get the current logged in user
     */
    fun getCurrentUser(): User {
        return findAll().get(0)
    }
}
