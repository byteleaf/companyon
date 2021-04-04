package de.byteleaf.companyon.absence.entity

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.entity.BaseEntity
import org.springframework.data.mongodb.core.mapping.Document

@NoArgConstructor
@Document(collection = "user-vacation-adjustments")
class UserVacationAdjustmentEntity(
    val user: String,
    val vacationAdjustmentInMinutes: Int
) : BaseEntity()