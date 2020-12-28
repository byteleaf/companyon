package de.byteleaf.companyon.common.event

import de.byteleaf.companyon.common.dto.BaseDTO
import de.byteleaf.companyon.common.entity.EntityType

data class EntityDeletedEvent<T : BaseDTO>(val entityType: EntityType, val entity: T)