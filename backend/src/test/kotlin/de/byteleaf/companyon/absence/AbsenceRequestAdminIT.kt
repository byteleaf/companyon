package de.byteleaf.companyon.absence

import de.byteleaf.companyon.absence.constant.AbsenceType
import de.byteleaf.companyon.absence.dto.AbsenceRequestGQLResponse
import de.byteleaf.companyon.absence.dto.input.AbsenceRequestInput
import de.byteleaf.companyon.absence.logic.AbsenceRequestService
import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.common.error.ErrorCode
import de.byteleaf.companyon.test.AbstractQueryMutationIT
import de.byteleaf.companyon.test.mock.SecurityContextServiceMock
import de.byteleaf.companyon.test.util.GQLErrorUtil
import graphql.Assert.assertNull
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Import(SecurityContextServiceMock::class)
@TestPropertySource(properties = ["app.non-sec-user-admin=true"])
class AbsenceRequestAdminIT : AbstractQueryMutationIT("absence/absence-request") {

    private val targetClass = AbsenceRequestGQLResponse::class.java

    @Autowired
    protected lateinit var absenceRequestService: AbsenceRequestService

    @Test
    fun approve() {
        val response = createAndApprove()
        assertThat(response.approval!!.user).isEqualTo(NonSecConfiguration.NON_SEC_USER_ID)
        assertThat(response.approval!!.timeStamp).isCloseToUtcNow(within(3, ChronoUnit.SECONDS))
    }

    @Test
    fun alreadyApproved() {
        val response = createAndApprove()
        val secondApprove = approve(response.id!!, true, true)
        GQLErrorUtil.expectError(secondApprove, ErrorCode.FATAL)
    }

    @Test
    fun unApprove() {
        val created = createAndApprove()
        val response = approve(created.id!!, false).get("$.data.approveAbsenceRequest", targetClass)
        assertNull(response.approval)
    }

    @Test
    fun alreadyUnapproved() {
        val created = create()
        val approve = approve(created.id, false, true)
        GQLErrorUtil.expectError(approve, ErrorCode.FATAL)
    }

    private fun create() = absenceRequestService.create(AbsenceRequestInput("a", NonSecConfiguration.NON_SEC_USER_ID, AbsenceType.VACATION, LocalDate.parse("2020-12-03")))
    private fun approve(id: String, approved: Boolean, skipErrors: Boolean = false) = performGQL("ApproveAbsenceRequest", mapOf(Pair("id", id), Pair("approved", approved)), skipErrors)
    private fun createAndApprove() = approve(create().id, true).get("$.data.approveAbsenceRequest", targetClass)
}