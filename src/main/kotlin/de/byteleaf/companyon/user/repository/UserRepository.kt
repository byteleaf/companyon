package de.byteleaf.companyon.user.repository

import de.byteleaf.companyon.user.entity.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface UserRepository : MongoRepository<UserEntity, String> {
    @Query
    fun findByOauth2Subject(oauth2Subject: String): UserEntity?

    @Query
    fun findByEmailIgnoreCase(email: String): UserEntity?
}