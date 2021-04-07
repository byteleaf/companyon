package de.byteleaf.companyon.absence

import de.byteleaf.companyon.absence.constant.AbsenceType
import de.byteleaf.companyon.absence.dto.AbsenceRequestGQLResponse
import de.byteleaf.companyon.absence.logic.AbsenceRequestService
import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.common.error.ErrorCode
import de.byteleaf.companyon.test.AbstractQueryMutationIT
import de.byteleaf.companyon.test.util.GQLErrorUtil
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class AbsenceRequestIT : AbstractQueryMutationIT("absence/absence-request") {

    private val targetClass = AbsenceRequestGQLResponse::class.java

    @Autowired
    protected lateinit var absenceRequestService: AbsenceRequestService

    @BeforeEach
    fun init() {
        absenceRequestService.deleteAll()
    }

    @Test
    fun create() {
        val response = performGQLByInput("CreateAbsenceRequest", mapOf(Pair("user", NonSecConfiguration.NON_SEC_USER_ID), Pair("description", "vacations in italy"), Pair("type", AbsenceType.VACATION.name), Pair("from", "2021-03-05"), Pair("to", "2021-03-05"), Pair("workingScheduleFirstDayInPercent", 60)))
            .get("$.data.createAbsenceRequest", targetClass)
        Assertions.assertThat(response.workingScheduleFirstDayInPercent).isEqualTo(60)
        Assertions.assertThat(response.workingScheduleLastDayInPercent).isEqualTo(100)
    }

    @Test
    fun createTestInputValidation() {
        val response = performGQLByInput("CreateAbsenceRequest", mapOf(Pair("user", NonSecConfiguration.NON_SEC_USER_ID), Pair("description", "vacations in italy"), Pair("type", AbsenceType.VACATION.name), Pair("from", "2021-03-05"), Pair("to", "2021-03-05"), Pair("workingScheduleFirstDayInPercent", 101)), true)
        GQLErrorUtil.expectError(response, ErrorCode.INPUT_VALIDATION)
    }


    // TEST checkIfStartsInPast

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
