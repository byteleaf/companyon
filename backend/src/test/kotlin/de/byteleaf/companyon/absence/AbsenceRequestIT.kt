package de.byteleaf.companyon.absence

import de.byteleaf.companyon.absence.constant.AbsenceType
import de.byteleaf.companyon.absence.dto.AbsenceRequestGQLResponse
import de.byteleaf.companyon.absence.logic.AbsenceRequestService
import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.auth.permission.PermissionType
import de.byteleaf.companyon.common.error.ErrorCode
import de.byteleaf.companyon.test.AbstractQueryMutationIT
import de.byteleaf.companyon.test.util.GQLErrorUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.format.DateTimeFormatter

class AbsenceRequestIT : AbstractQueryMutationIT("absence/absence-request") {

    private val targetClass = AbsenceRequestGQLResponse::class.java

    @Autowired
    protected lateinit var absenceRequestService: AbsenceRequestService

    private val DESC = Pair("description", "vacations in italy")
    private val USER = Pair("user", NonSecConfiguration.NON_SEC_USER_ID)
    private val TYPE = Pair("type", AbsenceType.VACATION.name)
    private val FROM = Pair("from", "2080-03-05")
    private val TO = Pair("to", "2080-03-06")

    @BeforeEach
    fun init() {
        absenceRequestService.deleteAll()
    }

    @Test
    fun create() {
        val response = performGQLByInput("CreateAbsenceRequest", mapOf(USER, DESC, TYPE, FROM, Pair("workingScheduleFirstDayInPercent", 60), TO)).get("$.data.createAbsenceRequest", targetClass)
        assertThat(response.to!!.format(DateTimeFormatter.ISO_LOCAL_DATE)).isEqualTo("2080-03-06")
        assertThat(response.workingScheduleFirstDayInPercent).isEqualTo(60)
        assertThat(response.workingScheduleLastDayInPercent).isEqualTo(100)
    }

    @Test
    fun createTestInputValidation() {
        val response = performGQLByInput("CreateAbsenceRequest", mapOf(USER, DESC, TYPE, FROM, Pair("workingScheduleFirstDayInPercent", 101)), true)
        GQLErrorUtil.expectError(response, ErrorCode.INPUT_VALIDATION)
    }

    @Test
    fun update() {
        val created = performGQLByInput("CreateAbsenceRequest", mapOf(USER, DESC, TYPE, FROM)).get("$.data.createAbsenceRequest", targetClass)
        val response = performGQLByIdAndInput("UpdateAbsenceRequest", created.id!!, mapOf(USER, FROM, TYPE, Pair("description", "vacations in spain"))).get("$.data.updateAbsenceRequest", targetClass)
        assertThat(response.description).isEqualTo("vacations in spain")
    }

    @Test
    fun createStartInPast() {
        val response = performGQLByInput("CreateAbsenceRequest", mapOf(USER, DESC, TYPE, Pair("from", "2020-03-05")), true);
        GQLErrorUtil.expectNoPermission(response, PermissionType.ADMIN)
    }

    @Test
    fun delete() {
        val created = performGQLByInput("CreateAbsenceRequest", mapOf(USER, DESC, TYPE, FROM)).get("$.data.createAbsenceRequest", targetClass)
        performGQLById("DeleteAbsenceRequest", created.id!!)
        assertThat(absenceRequestService.findAll().size).isEqualTo(0)
    }
}
