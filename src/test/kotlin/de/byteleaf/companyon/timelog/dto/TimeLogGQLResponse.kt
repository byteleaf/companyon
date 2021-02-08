package de.byteleaf.companyon.project.dto

import de.byteleaf.companyon.user.dto.User
import java.time.OffsetDateTime

class TimeLogGQLResponse(
    var id: String,
    var user: User?,
    val project: ProjectGQLResponse?,
    val start: String,
    val description: String?,
    val durationInMinutes: Int,
    val beakInMinutes: Int
)