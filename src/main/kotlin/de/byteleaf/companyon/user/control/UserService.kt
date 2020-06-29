package de.byteleaf.companyon.user.control

import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.input.UserInput
import org.springframework.stereotype.Service

@Service
class UserService {

    /**
     * To get the current logged in user
     */
    fun getCurrentUser(): User {
        return User(99, "Jeff", "Bytezos");
    }

    fun updateUser(id: Long, input: UserInput): User {
        return getCurrentUser()
    }

    fun createUser(input: UserInput): User {
        return getCurrentUser()
    }
}