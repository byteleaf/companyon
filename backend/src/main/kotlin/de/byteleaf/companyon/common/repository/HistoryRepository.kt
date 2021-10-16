package de.byteleaf.companyon.common.repository

import de.byteleaf.companyon.common.entity.HistoryEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface HistoryRepository : MongoRepository<HistoryEntity, String> {
    
}