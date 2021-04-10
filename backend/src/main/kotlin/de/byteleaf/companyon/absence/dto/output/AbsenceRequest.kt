package de.byteleaf.companyon.absence.dto.output

import de.byteleaf.companyon.absence.constant.AbsenceType
import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO
import java.time.LocalDate

@NoArgConstructor
class AbsenceRequest(
    override val id: String,
    val description: String,
    val user: String,
    val type: AbsenceType,
    val from: LocalDate,
    val workingScheduleFirstDayInPercent: Int,
    val to: LocalDate,
    val workingScheduleLastDayInPercent: Int
) : BaseDTO()