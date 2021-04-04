package de.byteleaf.companyon.absence.entity

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.entity.BaseEntity
import org.springframework.data.mongodb.core.mapping.Document
import java.time.OffsetDateTime

@NoArgConstructor
@Document(collection = "absence-requests")
class AbsenceRequestEntity(
    val description: String,
    val user: String,
    val type: AbsenceType,
    val from: OffsetDateTime,
    val absenceFirstDayInMinutes: Int,
    val to: OffsetDateTime,
    val absenceLastDayInMinutes: Int,
    var approvedBy: String?
) : BaseEntity()