package de.byteleaf.companyon.vacation.repository

import de.byteleaf.companyon.vacation.entity.VacationRequestEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface VacationRequestRepository : MongoRepository<VacationRequestEntity, String>