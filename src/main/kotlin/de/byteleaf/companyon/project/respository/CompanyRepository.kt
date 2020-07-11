package de.byteleaf.companyon.project.respository

import de.byteleaf.companyon.project.entity.ProjectEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface ProjectRepository : MongoRepository<ProjectEntity, String>