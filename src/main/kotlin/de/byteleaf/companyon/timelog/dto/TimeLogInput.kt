package de.byteleaf.companyon.timelog.dto

import java.time.OffsetDateTime

class TimeLogInput(
    val user: String,
    val project: String,
    val start: OffsetDateTime,
    val durationInMinutes: Int,
    var description: String?,
    val breakInMinutes: Int = 0
)