package de.byteleaf.companyon.user.repository

import de.byteleaf.companyon.user.entity.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<UserEntity, Long> {

}