package de.byteleaf.companyon.timelog.dto

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO
import de.byteleaf.companyon.project.entity.ProjectState
import java.time.OffsetDateTime

@NoArgConstructor
class TimeLog(
    val user: String,
    val project: String,
    val start: OffsetDateTime,
    val description: String?,
    val durationInMinutes: Int,
    val beakInMinutes: Int
) : BaseDTO()