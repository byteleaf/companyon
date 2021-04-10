package de.byteleaf.companyon.absence.repository

import de.byteleaf.companyon.absence.entity.AbsenceRequestEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface AbsenceRequestRepository : MongoRepository<AbsenceRequestEntity, String>