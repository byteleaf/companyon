package de.byteleaf.companyon.common.event

import de.byteleaf.companyon.common.entity.BaseEntity

data class EntityCreatedEvent(val b  : Boolean, val cls: Class<*>,val id: String)