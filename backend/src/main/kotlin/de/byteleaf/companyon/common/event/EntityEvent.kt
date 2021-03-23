package de.byteleaf.companyon.common.event

import de.byteleaf.companyon.common.dto.BaseDTO
import de.byteleaf.companyon.common.entity.EntityType

abstract class EntityEvent<T : BaseDTO>(val entityType: EntityType, val entity: T, val eventType: EventType)