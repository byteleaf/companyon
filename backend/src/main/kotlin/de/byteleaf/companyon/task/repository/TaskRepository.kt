package de.byteleaf.companyon.task.repository

import de.byteleaf.companyon.task.entity.TaskEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository : MongoRepository<TaskEntity, String>