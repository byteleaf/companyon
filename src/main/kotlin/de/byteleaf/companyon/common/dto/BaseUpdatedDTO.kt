package de.byteleaf.companyon.common.dto

import de.byteleaf.companyon.common.event.EventType

abstract class BaseUpdatedDTO<T : BaseDTO>(var type: EventType? = null, var entity: T? = null)