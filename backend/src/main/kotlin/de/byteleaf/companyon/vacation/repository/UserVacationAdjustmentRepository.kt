package de.byteleaf.companyon.vacation.repository

import de.byteleaf.companyon.vacation.entity.UserVacationAdjustmentEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface UserVacationAdjustmentRepository : MongoRepository<UserVacationAdjustmentEntity, String>