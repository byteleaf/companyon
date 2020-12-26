package de.byteleaf.companyon.common.event


import de.byteleaf.companyon.common.entity.BaseEntity
import de.byteleaf.companyon.company.entity.CompanyEntity

class EntityDeletedEvent(val eventEntityType: Class<*>, val deletedObjectId: String)