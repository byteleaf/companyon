package de.byteleaf.companyon.timelog.dto

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO
import java.time.OffsetDateTime

@NoArgConstructor
class TimeLog(
    override val id: String,
    val user: String,
    val project: String,
    val start: OffsetDateTime,
    val description: String?,
    val durationInMinutes: Int,
    val breakInMinutes: Int
) : BaseDTO()
