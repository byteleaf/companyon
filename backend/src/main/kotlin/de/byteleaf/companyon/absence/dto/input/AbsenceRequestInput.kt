package de.byteleaf.companyon.absence.dto.input

import de.byteleaf.companyon.absence.constant.AbsenceType
import java.time.LocalDate

class AbsenceRequestInput(
    val description: String,
    val user: String,
    val type: AbsenceType,
    val from: LocalDate,
    var absenceFirstDayInMinutes: Int?,
    val to: LocalDate,
    var absenceLastDayInMinutes: Int?
)