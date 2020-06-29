package de.byteleaf.companyon.user.repository

import de.byteleaf.companyon.user.entity.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface UserRepository : MongoRepository<UserEntity, Long> {

   override fun findById(id: Long): Optional<UserEntity>

}