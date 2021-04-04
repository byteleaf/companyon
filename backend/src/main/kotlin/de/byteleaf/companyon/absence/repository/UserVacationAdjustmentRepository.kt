package de.byteleaf.companyon.absence.repository

import de.byteleaf.companyon.absence.entity.UserVacationAdjustmentEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface UserVacationAdjustmentRepository : MongoRepository<UserVacationAdjustmentEntity, String> {

    fun findByUserIn(userIds: Collection<String>): List<UserVacationAdjustmentEntity>
}