package de.byteleaf.companyon.absence

import de.byteleaf.companyon.absence.constant.AbsenceType
import de.byteleaf.companyon.absence.dto.AbsenceRequestGQLResponse
import de.byteleaf.companyon.absence.dto.input.AbsenceRequestInput
import de.byteleaf.companyon.absence.logic.AbsenceRequestService
import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.auth.permission.PermissionType
import de.byteleaf.companyon.test.AbstractQueryMutationIT
import de.byteleaf.companyon.test.util.GQLErrorUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

class GetAbsenceRequestsIT : AbstractQueryMutationIT("absence/absence-request") {

    private val targetClass = AbsenceRequestGQLResponse::class.java

    @Autowired
    protected lateinit var absenceRequestService: AbsenceRequestService

    private val USER_IDS = Pair("userIds", listOf(NonSecConfiguration.NON_SEC_USER_ID))
    private val TYPES = Pair("types", listOf(AbsenceType.VACATION))
    private val FROM = Pair("from", "2020-12-04")

    @BeforeEach
    fun init() {
        absenceRequestService.deleteAll()
        absenceRequestService.create(AbsenceRequestInput("a", NonSecConfiguration.NON_SEC_USER_ID, AbsenceType.VACATION, LocalDate.parse("2020-12-03")))
        absenceRequestService.create(AbsenceRequestInput("b", NonSecConfiguration.NON_SEC_USER_ID, AbsenceType.VACATION, LocalDate.parse("2020-12-04"), 100, LocalDate.parse("2020-12-06"), 100))
        absenceRequestService.create(AbsenceRequestInput("c", NonSecConfiguration.NON_SEC_USER_ID, AbsenceType.SICKNESS, LocalDate.parse("2020-12-05"), 100, LocalDate.parse("2020-12-07"), 100))

    }

    @Test
    fun unauthorizedUserId() {
        val response = performGQL("GetAbsenceRequests", mapOf(Pair("userIds", listOf("UNAUTHORIZED_USER_ID"))), true)
        GQLErrorUtil.expectNoPermission(response, PermissionType.CURRENT_USER_OR_ADMIN)
    }

    @Test
    fun userId() {
        assertThat(performGQL("GetAbsenceRequests", mapOf(USER_IDS)).getList("$.data.absenceRequests", targetClass).size).isEqualTo(3)
    }

    @Test
    fun types() {
        assertThat(performGQL("GetAbsenceRequests", mapOf(USER_IDS, TYPES)).getList("$.data.absenceRequests", targetClass).size).isEqualTo(2)
        assertThat(performGQL("GetAbsenceRequests", mapOf(USER_IDS, Pair("types", listOf(AbsenceType.VACATION, AbsenceType.SICKNESS)))).getList("$.data.absenceRequests", targetClass).size).isEqualTo(3)
    }

    @Test
    fun from() {
        assertThat(performGQL("GetAbsenceRequests", mapOf(USER_IDS, Pair("from", "2020-12-03"))).getList("$.data.absenceRequests", targetClass).size).isEqualTo(3)
        assertThat(performGQL("GetAbsenceRequests", mapOf(USER_IDS, FROM)).getList("$.data.absenceRequests", targetClass).size).isEqualTo(2)
    }

    @Test
    fun fromAndTo() {
        assertThat(performGQL("GetAbsenceRequests", mapOf(USER_IDS, FROM, Pair("to", "2020-12-06"))).getList("$.data.absenceRequests", targetClass).size).isEqualTo(1)
    }

    @Test
    fun fromAndTypes() {
        assertThat(performGQL("GetAbsenceRequests", mapOf(USER_IDS, FROM, TYPES)).getList("$.data.absenceRequests", targetClass).size).isEqualTo(1)
    }

    // TODO
//    @Test
//    fun approved() {
//        assertThat(performGQL("GetAbsenceRequests", mapOf(USER_IDS)).getList("$.data.absenceRequests", targetClass).size).isEqualTo(3)
//    }
}