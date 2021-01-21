package de.byteleaf.companyon.company.logic

import de.byteleaf.companyon.common.control.AbstractEventDataService
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.CompanyUpdate
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.entity.CompanyEntity
import de.byteleaf.companyon.company.respository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyService : AbstractEventDataService<CompanyEntity, Company, CompanyUpdate, CompanyInput, CompanyRepository>() {
    override fun getEntityType(): EntityType = EntityType.COMPANY
}