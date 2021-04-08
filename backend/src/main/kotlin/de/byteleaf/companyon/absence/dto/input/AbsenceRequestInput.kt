package de.byteleaf.companyon.absence.dto.input

import de.byteleaf.companyon.absence.constant.AbsenceType
import org.hibernate.validator.constraints.Range
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class AbsenceRequestInput(
    @field:NotBlank
    val description: String,
    @field:NotBlank
    val user: String,
    @field:NotNull
    val type: AbsenceType,
    @field:NotNull
    val from: LocalDate,
    @field:Range(min = 0, max = 100)
    val workingScheduleFirstDayInPercent: Int = 100,
    // TODO must be bigger than from (if set) -> will be done during https://github.com/byteleaf/companyon/issues/65
    var to: LocalDate?,
    @field:Range(min = 0, max = 100)
    val workingScheduleLastDayInPercent: Int = 100
)