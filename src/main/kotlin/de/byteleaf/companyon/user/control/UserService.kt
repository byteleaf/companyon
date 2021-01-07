package de.byteleaf.companyon.user.control

import de.byteleaf.companyon.common.control.AbstractEventDataService
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.event.EntityCreatedEvent
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.UserUpdate
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.entity.UserEntity
import de.byteleaf.companyon.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService : AbstractEventDataService<UserEntity, User, UserUpdate, UserInput, UserRepository>() {

    @Autowired
    private lateinit var securityService: SecurityService

    override fun getEntityType(): EntityType = EntityType.USER

    /**
     * To get the current logged in user
     */
    fun getCurrentUser(): User {
        return findByOauth2Subject(securityService.getCurrentAuth2Subject())!!
    }

    fun findByOauth2Subject(oauth2Subject: String): User? {
        val result = repository.findByOauth2Subject(oauth2Subject) ?: return null
        return entityToOutput(result)
    }

    /**
     * try to find a user by email
     */
    fun findByEmailAndUpdateOAuthSubject(email: String, oauth2Subject: String): User? {
        val entity = repository.findByEmailIgnoreCase(email) ?: return null
        entity.oauth2Subject = oauth2Subject
        repository.save(entity)
        return entityToOutput(entity)
    }

    override fun create(input: UserInput): User = create(input, null)

    fun create(input: UserInput, oauth2Subject: String? = null): User {
        val entity = inputToEntity(input)
        entity.oauth2Subject = oauth2Subject
        val dto = entityToOutput(repository.insert(entity))
        applicationEventPublisher.publishEvent(EntityCreatedEvent(getEntityType(), dto))
        return dto
    }
}
