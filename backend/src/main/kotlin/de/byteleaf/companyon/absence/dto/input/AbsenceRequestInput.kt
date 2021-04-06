package de.byteleaf.companyon.absence.dto.input

import de.byteleaf.companyon.absence.constant.AbsenceType
import java.time.LocalDate

class AbsenceRequestInput(
    val description: String,
    val user: String,
    val type: AbsenceType,
    val from: LocalDate,
    val workingScheduleFirstDayInPercent: Int = 100,
    val to: LocalDate,
    val workingScheduleLastDayInPercent: Int = 100
)