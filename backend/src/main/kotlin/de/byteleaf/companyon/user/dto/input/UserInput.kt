package de.byteleaf.companyon.user.dto.input

import de.byteleaf.companyon.fileupload.dto.input.FileMetaInput
import de.byteleaf.companyon.user.constant.Country
import de.byteleaf.companyon.user.constant.ProvinceGermany

class UserInput(
    val firstName: String,
    val lastName: String,
    val email: String,
    val admin: Boolean = false,
    val signature: FileMetaInput? = null,
    val avatar: FileMetaInput? = null,
    val weeklyWorkingMinutes: Int = 2400,
    val vacationDaysPerYear: Int = 30,
    val country: Country = Country.GERMANY,
    val province: ProvinceGermany = ProvinceGermany.BAYERN
)
