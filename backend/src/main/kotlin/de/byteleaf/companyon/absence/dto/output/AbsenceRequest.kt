package de.byteleaf.companyon.absence.dto.output

import de.byteleaf.companyon.absence.constant.AbsenceType
import de.byteleaf.companyon.auth.dto.Approval
import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO
import java.time.LocalDate

@NoArgConstructor
class AbsenceRequest(
    override val id: String,
    val description: String,
    /**
     * @see de.byteleaf.companyon.user.boundary.fieldresolver.AbsenceRequestFieldResolver.getUser
     */
    val user: String,
    val type: AbsenceType,
    val from: LocalDate,
    val workingScheduleFirstDayInPercent: Int,
    val to: LocalDate,
    val workingScheduleLastDayInPercent: Int,
    val approval: Approval?
) : BaseDTO()