package de.byteleaf.companyon.absence.dto

import de.byteleaf.companyon.absence.constant.AbsenceType
import de.byteleaf.companyon.user.dto.UserGQLResponse
import java.time.LocalDate

class AbsenceRequestGQLResponse(
    var id: String?,
    var description: String?,
    var user: UserGQLResponse?,
    var type: AbsenceType?,
    var from: LocalDate?,
    var workingScheduleFirstDayInPercent: Int?,
    var to: LocalDate?,
    var workingScheduleLastDayInPercent: Int?
)