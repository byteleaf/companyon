package de.byteleaf.companyon.project.boundary

import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.CompanyUpdated
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired

class ProjectSubscriptionResolver {

    @Autowired
    private lateinit var companyService: CompanyService

    fun projectUpdated(): Publisher<CompanyUpdated> = companyService.getPublisher()
}