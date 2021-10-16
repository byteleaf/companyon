package de.byteleaf.companyon.timelog.dto

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO
import java.time.OffsetDateTime

@NoArgConstructor
class TimeLog(
    override val id: String,
    /**
     * @see de.byteleaf.companyon.user.boundary.fieldresolver.TimeLogUserFieldResolver.getUser
     */
    val user: String,
    /**
     * @see de.byteleaf.companyon.project.boundary.TimeLogProjectFieldResolver.getProject
     */
    val project: String,
    val start: OffsetDateTime,
    val description: String?,
    val durationInMinutes: Int,
    val breakInMinutes: Int
) : BaseDTO()
