package de.byteleaf.companyon.timelog.dto

import de.byteleaf.companyon.project.entity.ProjectState
import java.time.OffsetDateTime

class TimeLogInput(
    val user: String,
    val project: String,
    val start: OffsetDateTime,
    val durationInMinutes: Int,
    var description: String? = null,
    val beakInMinutes: Int = 0
)