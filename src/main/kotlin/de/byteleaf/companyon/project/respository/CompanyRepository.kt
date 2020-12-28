package de.byteleaf.companyon.project.respository

import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.project.entity.ProjectEntity
import org.springframework.data.mongodb.repository.DeleteQuery
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query


interface ProjectRepository : MongoRepository<ProjectEntity, String> {

    @DeleteQuery
    fun deleteByCompany(companyId: String)

    @Query
    fun findByCompany(companyId: String): List<ProjectEntity>
}