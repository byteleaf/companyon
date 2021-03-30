package de.byteleaf.companyon.vacation.dto.output

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO
import java.time.OffsetDateTime

@NoArgConstructor
class VacationRequest(
    override val id: String,
    val description: String,
    val from: OffsetDateTime,
    // TODO if not set the default should be calculated by the default work hours per week of each employee
    val vacationMinutesFirstDay: Int,
    val to: OffsetDateTime,
    val vacationMinutesLastDay: Int,
    val approvedBy: String
) : BaseDTO()