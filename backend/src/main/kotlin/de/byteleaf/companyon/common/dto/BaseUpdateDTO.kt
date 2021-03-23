package de.byteleaf.companyon.common.dto

abstract class BaseUpdateDTO<T : BaseDTO>(var type: EntityUpdateType? = null, var entity: T? = null)