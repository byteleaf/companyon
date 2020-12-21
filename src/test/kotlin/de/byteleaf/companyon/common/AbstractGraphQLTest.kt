package de.byteleaf.companyon.common

import de.byteleaf.companyon.CompanyonTextContextConfiguration
import de.byteleaf.companyon.company.respository.CompanyRepository
import de.byteleaf.companyon.project.respository.ProjectRepository
import de.byteleaf.companyon.user.repository.UserRepository
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [CompanyonTextContextConfiguration::class])
abstract class AbstractGraphQLTest {
    @MockBean
    protected lateinit var userRepository: UserRepository

    @MockBean
    protected lateinit var companyRepository: CompanyRepository

    @MockBean
    protected lateinit var projectRepository: ProjectRepository
}