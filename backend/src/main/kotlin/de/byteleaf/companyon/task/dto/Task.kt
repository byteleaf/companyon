package de.byteleaf.companyon.task.dto

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO
import de.byteleaf.companyon.task.entity.TaskState

@NoArgConstructor
data class Task(
    override val id: String,
    /**
     * @see de.byteleaf.companyon.user.boundary.fieldresolver.TaskUserFieldResolver.getUser
     */
    val user: String,
    val description: String,
    val status: TaskState
) : BaseDTO()
