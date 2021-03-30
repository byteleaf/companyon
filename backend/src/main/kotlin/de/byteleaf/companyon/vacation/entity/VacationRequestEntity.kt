package de.byteleaf.companyon.vacation.entity

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.entity.BaseEntity
import org.springframework.data.mongodb.core.mapping.Document
import java.time.OffsetDateTime

@NoArgConstructor
@Document(collection = "vacation-requests")
class VacationRequestEntity(
    val user: String,
    val description: String,
    val from: OffsetDateTime,
    val vacationMinutesFirstDay: Int,
    val to: OffsetDateTime,
    val vacationMinutesLastDay: Int,
    var approvedBy: String?
) : BaseEntity()