package de.byteleaf.companyon.company.control

import de.byteleaf.companyon.common.control.AbstractAdminDataService
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.entity.CompanyEntity
import de.byteleaf.companyon.company.respository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyService : AbstractAdminDataService<CompanyEntity, Company, CompanyInput, CompanyRepository>() {


}
