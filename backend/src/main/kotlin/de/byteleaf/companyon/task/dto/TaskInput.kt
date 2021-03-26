package de.byteleaf.companyon.task.dto

import de.byteleaf.companyon.task.entity.TaskState

data class TaskInput(
    val user: String,
    val description: String,
    val status: TaskState = TaskState.OPEN
)
