package de.byteleaf.companyon.absence.dto.input

import java.time.OffsetDateTime

class AbsenceRequestInput(
    val description: String,
    val user: String,
    val from: OffsetDateTime,
    var absenceFirstDayInMinutes: Int?,
    val to: OffsetDateTime,
    var absenceLastDayInMinutes: Int?
)