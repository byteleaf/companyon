package de.byteleaf.companyon.absence.entity

import de.byteleaf.companyon.absence.constant.AbsenceType
import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.entity.BaseEntity
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@NoArgConstructor
@Document(collection = "absence-requests")
class AbsenceRequestEntity(
    val description: String,
    val user: String,
    val type: AbsenceType,
    val from: LocalDate,
    val workingScheduleFirstDayInPercent: Int,
    var to: LocalDate?,
    val workingScheduleLastDayInPercent: Int
) : BaseEntity()