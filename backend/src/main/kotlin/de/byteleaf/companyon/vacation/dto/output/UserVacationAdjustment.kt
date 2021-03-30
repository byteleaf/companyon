package de.byteleaf.companyon.vacation.dto.output

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO

@NoArgConstructor
class UserVacationAdjustment(
    override val id: String,
    val user: String,
    val vacationAdjustmentInMinutes: Int
) : BaseDTO()