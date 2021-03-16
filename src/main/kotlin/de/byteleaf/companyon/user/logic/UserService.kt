package de.byteleaf.companyon.user.logic

import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.event.EntityCreatedEvent
import de.byteleaf.companyon.common.logic.AbstractEventDataService
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.UserUpdate
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.entity.UserEntity
import de.byteleaf.companyon.user.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.oauth2.server.resource.BearerTokenError
import org.springframework.stereotype.Service


@Service
class UserService : AbstractEventDataService<UserEntity, User, UserUpdate, UserInput, UserRepository>() {

    override fun getEntityType(): EntityType = EntityType.USER

    fun findByOAuth2Subject(oauth2Subject: String): User? {
        val result = repository.findByOauth2Subject(oauth2Subject) ?: return null
        return entityToOutput(result)
    }

    /**
     * Tries to find a new user by email address. If the user already has oauth subject an error
     * will be thrown, it will only work for users without subject.
     */
    fun activateNewUser(email: String, oauth2Subject: String): User {
        val entity = repository.findByEmailIgnoreCase(email) ?: throw OAuth2AuthenticationException(
            BearerTokenError("NO_USER_FOUND_FOR_EMAIL", HttpStatus.FORBIDDEN, "No user found for the delivered email address!", ""))
        if (entity.oauth2Subject != null) throw OAuth2AuthenticationException(
            BearerTokenError("USER_ALREADY_EXISTING", HttpStatus.FORBIDDEN, "A user with identical email address is already existing!", "")
        )
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

    fun deleteAll(vararg excludedIds: String) = repository.deleteAllByIdNotIn(excludedIds.asList())
}
