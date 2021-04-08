package de.byteleaf.companyon.user.dto

import de.byteleaf.companyon.fileupload.dto.FileMeta
import de.byteleaf.companyon.user.constant.Country
import de.byteleaf.companyon.user.constant.ProvinceGermany

class UserGQLResponse(
    val id: String?,
    val oauth2Subject: String?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val admin: Boolean?,
    val signature: FileMeta?,
    val avatar: FileMeta?,
    val weeklyWorkingMinutes: Int?,
    val vacationDaysPerYear: Int?,
    val country: Country?,
    val province: ProvinceGermany?
)
