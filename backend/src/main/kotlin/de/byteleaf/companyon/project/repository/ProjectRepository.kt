package de.byteleaf.companyon.project.repository

import de.byteleaf.companyon.project.entity.ProjectEntity
import org.springframework.data.mongodb.repository.DeleteQuery
import org.springframework.data.mongodb.repository.MongoRepository

interface ProjectRepository : MongoRepository<ProjectEntity, String> {

    @DeleteQuery
    fun deleteByCompany(companyId: String): List<ProjectEntity>
    
    fun findByCompanyIn(companies: Collection<String>): List<ProjectEntity>
}
