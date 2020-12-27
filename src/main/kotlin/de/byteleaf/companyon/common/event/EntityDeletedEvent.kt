package de.byteleaf.companyon.common.event

import de.byteleaf.companyon.common.entity.BaseEntity

data class EntityDeletedEvent<T: BaseEntity>(val entityType: EventEntityType, val id: String, val entity: T)