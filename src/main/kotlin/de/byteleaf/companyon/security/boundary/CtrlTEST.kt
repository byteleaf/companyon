package de.byteleaf.companyon.security.boundary

import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.project.control.ProjectService
import de.byteleaf.companyon.user.control.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CtrlTEST {

    @Autowired
    private lateinit var companyService: CompanyService

    @GetMapping("test")
    fun test() = companyService.findAll()

    @GetMapping("test2")
    fun test2() = companyService.findAll()
}