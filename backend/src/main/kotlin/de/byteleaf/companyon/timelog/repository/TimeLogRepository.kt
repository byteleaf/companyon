package de.byteleaf.companyon.timelog.repository

import de.byteleaf.companyon.timelog.entity.TimeLogEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface TimeLogRepository : MongoRepository<TimeLogEntity, String>
