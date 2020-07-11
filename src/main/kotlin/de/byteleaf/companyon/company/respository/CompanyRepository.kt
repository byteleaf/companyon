package de.byteleaf.companyon.company.respository

import de.byteleaf.companyon.company.entity.CompanyEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface CompanyRepository : MongoRepository<CompanyEntity, String>