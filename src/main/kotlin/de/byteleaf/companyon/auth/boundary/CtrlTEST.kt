package de.byteleaf.companyon.auth.boundary

import de.byteleaf.companyon.company.control.CompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CtrlTEST {

    @Autowired
    private lateinit var companyService: CompanyService

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("test")
    fun test() = companyService.findAll()

    @GetMapping("test2")
    fun test2() = companyService.findAll()
}