package de.byteleaf.companyon.absence.dto.output

import de.byteleaf.companyon.absence.entity.AbsenceType
import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO
import java.time.OffsetDateTime

@NoArgConstructor
class AbsenceRequest(
    override val id: String,
    val description: String,
    val user: String,
    val type: AbsenceType,
    val from: OffsetDateTime,
    val absenceFirstDayInMinutes: Int,
    val to: OffsetDateTime,
    val absenceLastDayInMinutes: Int,
    val approvedBy: String?
) : BaseDTO()