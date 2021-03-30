package de.byteleaf.companyon.vacation.dto.input

import java.time.OffsetDateTime

class VacationRequestInput(
    val description: String,
    val from: OffsetDateTime,
    var vacationMinutesFirstDay: Int?,
    val to: OffsetDateTime,
    var vacationMinutesLastDay: Int?
)