package de.byteleaf.companyon.company.control

import de.byteleaf.companyon.common.control.AbstractDataService
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.entity.CompanyEntity
import de.byteleaf.companyon.company.respository.CompanyRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class CompanyService : AbstractDataService<CompanyEntity, Company, CompanyInput, CompanyRepository>() {


    @PreAuthorize("hasAuthority('admin')")
    override fun create(input: CompanyInput) = super.create(input)

}
