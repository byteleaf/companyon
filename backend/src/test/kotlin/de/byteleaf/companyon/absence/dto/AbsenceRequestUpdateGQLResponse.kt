package de.byteleaf.companyon.absence.dto

import de.byteleaf.companyon.common.dto.EntityUpdateType

class AbsenceRequestUpdateGQLResponse(
    var type: EntityUpdateType,
    var entity: AbsenceRequestGQLResponse
)