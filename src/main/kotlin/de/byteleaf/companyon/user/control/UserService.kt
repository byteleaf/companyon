package de.byteleaf.companyon.user.control

import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.entity.UserEntity
import de.byteleaf.companyon.user.repository.UserRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import kotlin.random.Random.Default.nextBoolean

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var modelMapper: ModelMapper

    /**
     * To get the current logged in user
     */
    fun getCurrentUser(): User {
        val currentUser = User(99, "Jeff", "Bytezos");
        if (nextBoolean()) {
            currentUser.avatarUrl = "https://profile-images.xing.com/images/e9347adcf390f13f4809c7b08653a73b-2/simon-ittmann.1024x1024.jpg"
        }
        else {
            currentUser.avatarUrl = "https://media.graphcms.com/resize=width:100/25QFLjz6RZOUejx8DBD6"
        }

        return currentUser
    }

    fun updateUser(id: Long, input: UserInput): User {
        return getCurrentUser()
    }

    fun createUser(input: UserInput): User {
        val users = userRepository.findAll()
        val userEntity = modelMapper.map(input, UserEntity::class.java)
        userRepository.insert(userEntity)
        return getCurrentUser()
    }
}