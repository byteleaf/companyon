package de.byteleaf.companyon.company.access

import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.CompanyUpdate
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.logic.CompanyService
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CompanyAccessService {

    @Autowired
    private lateinit var companyService: CompanyService

    fun get(id: String): Company = companyService.get(id)

    fun findAll(): List<Company> = companyService.findAll()

    fun create(input: CompanyInput): Company = companyService.create(input)

    fun update(id: String, input: CompanyInput): Company = companyService.update(id, input)

    fun delete(id: String): Company = companyService.delete(id)

    fun getPublisher(): Publisher<CompanyUpdate> = companyService.getPublisher()
}