package de.byteleaf.companyon.common.repository

import de.byteleaf.companyon.common.entity.HistoryEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface HistoryRepository : MongoRepository<HistoryEntity, String> {

    // TODO
//    @Query("{ 'validFrom' : { \$lt: ?0, \$gt: ?1 }, 'entityId': ?2 }")
//    fun findNewerVersions(now: OffsetDateTime, currentUsed: OffsetDateTime, entityId: String): List<HistoryEntity>
}