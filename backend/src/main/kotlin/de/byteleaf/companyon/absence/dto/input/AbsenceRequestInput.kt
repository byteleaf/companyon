package de.byteleaf.companyon.absence.dto.input

import de.byteleaf.companyon.absence.constant.AbsenceType
import org.hibernate.validator.constraints.Range
import java.time.LocalDate

class AbsenceRequestInput(
    val description: String,
    val user: String,
    val type: AbsenceType,
    val from: LocalDate,
    @field:Range(min = 0, max = 100)
    val workingScheduleFirstDayInPercent: Int = 100,
    val to: LocalDate,
    @field:Range(min = 0, max = 100)
    val workingScheduleLastDayInPercent: Int = 100
)