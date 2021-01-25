package de.byteleaf.companyon.timelog.dto

import de.byteleaf.companyon.project.entity.ProjectState
import java.time.OffsetDateTime

class TimeLogInput(
    val user: String,
    val project: String,
    val start: OffsetDateTime,
    val description: String?,
    val durationInMinutes: Int,
    val beakInMinutes: Int = 0
)