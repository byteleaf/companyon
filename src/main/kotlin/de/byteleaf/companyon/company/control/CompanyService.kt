package de.byteleaf.companyon.company.control

import de.byteleaf.companyon.common.control.AbstractEventDataService
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.CompanyUpdate
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.entity.CompanyEntity
import de.byteleaf.companyon.company.respository.CompanyRepository
import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Service


@Service
open class CompanyService : AbstractEventDataService<CompanyEntity, Company, CompanyUpdate, CompanyInput, CompanyRepository>() {
    override fun getEntityType(): EntityType = EntityType.COMPANY

    @Secured("ROLE_ADMIN")
    open override fun findAll(): List<Company> {
        return super.findAll()
    }
}