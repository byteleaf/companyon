package de.byteleaf.companyon.user.dto

import de.byteleaf.companyon.common.dto.EntityUpdateType

class UserUpdateGQLResponse(
    var type: EntityUpdateType,
    var entity: UserGQLResponse
)