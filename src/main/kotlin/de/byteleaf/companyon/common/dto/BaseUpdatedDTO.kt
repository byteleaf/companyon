package de.byteleaf.companyon.common.dto

abstract class BaseUpdatedDTO<T : BaseDTO>(var type: EntityUpdateType? = null, var entity: T? = null)