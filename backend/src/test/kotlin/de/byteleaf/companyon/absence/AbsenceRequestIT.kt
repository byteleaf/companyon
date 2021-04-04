package de.byteleaf.companyon.absence

import de.byteleaf.companyon.absence.dto.AbsenceRequestGQLResponse
import de.byteleaf.companyon.test.AbstractQueryMutationIT
import de.byteleaf.companyon.user.logic.UserService
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired

class AbsenceRequestIT : AbstractQueryMutationIT("absence/absence-request") {

    private val targetClass = AbsenceRequestGQLResponse::class.java

    @Autowired
    protected lateinit var userService: UserService

    @BeforeEach
    fun init() {

    }
//
//    @Test
//    fun create() {
//        performGQLByInput(
//            "CreateTimeLog",
//            mapOf(
//                Pair("start", "2011-12-03T10:15:30+01:00"),
//                Pair("user", userId),
//                Pair("project", project1.id), Pair("description", "A"), Pair("durationInMinutes", 60), Pair("breakInMinutes", 15)
//            )
//        ).get("$.data.createTimeLog", targetClass)
//    }
//
//    @Test
//    fun delete() {
//        val createdEntity = createTimeLog()
//        Assertions.assertThat(timeLogService.findAll().size).isEqualTo(1)
//        performGQLById("DeleteTimeLog", createdEntity.id!!)
//        Assertions.assertThat(timeLogService.findAll().size).isEqualTo(0)
//    }

//    private fun seedTestData() {
//        project1 = projectService.create(ProjectInput("Project A", companyService.create(CompanyInput("Company A")).id))
//    }
}
