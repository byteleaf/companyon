package de.byteleaf.companyon.user.repository

import de.byteleaf.companyon.user.entity.UserEntity
import org.springframework.data.mongodb.repository.DeleteQuery
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<UserEntity, String> {

    fun findByOauth2Subject(oauth2Subject: String): UserEntity?

    fun findByEmailIgnoreCase(email: String): UserEntity?

    @DeleteQuery
    fun deleteAllByIdNotIn(excludedIds: Collection<String>)
}
