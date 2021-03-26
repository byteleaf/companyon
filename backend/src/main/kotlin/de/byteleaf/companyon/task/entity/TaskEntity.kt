package de.byteleaf.companyon.task.entity

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.entity.BaseEntity
import org.springframework.data.mongodb.core.mapping.Document

@NoArgConstructor
@Document(collection = "tasks")
data class TaskEntity(val user: String, val description: String, val status: TaskState) :
    BaseEntity()
