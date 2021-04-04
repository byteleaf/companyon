package de.byteleaf.companyon.absence.dto.output

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO

@NoArgConstructor
class UserVacationAdjustment(
    override val id: String,
    val user: String,
    val description: String,
    val vacationAdjustmentInMinutes: Int
    // TODO Year mapping Map<Year, int> // TODO


    /**
     * yearSpecificAdjustments:
     * 2021 {
     *   "merkel": 8,
     *   "stoiber": 9
     * rename absence
     *
     *
     *
     */
) : BaseDTO()