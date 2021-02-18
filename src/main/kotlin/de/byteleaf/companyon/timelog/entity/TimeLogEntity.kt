package de.byteleaf.companyon.timelog.entity

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.entity.BaseEntity
import org.springframework.data.mongodb.core.mapping.Document
import java.time.OffsetDateTime

@NoArgConstructor
@Document(collection = "time-logs")
class TimeLogEntity(
    val user: String,
    val project: String,
    val start: OffsetDateTime,
    val description: String?,
    val durationInMinutes: Int,
    val beakInMinutes: Int
): BaseEntity()