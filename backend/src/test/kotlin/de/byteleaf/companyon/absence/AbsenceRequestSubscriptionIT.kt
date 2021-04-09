package de.byteleaf.companyon.absence

import de.byteleaf.companyon.absence.constant.AbsenceType
import de.byteleaf.companyon.absence.dto.AbsenceRequestUpdateGQLResponse
import de.byteleaf.companyon.absence.dto.input.AbsenceRequestInput
import de.byteleaf.companyon.absence.dto.output.AbsenceRequest
import de.byteleaf.companyon.absence.logic.AbsenceRequestService
import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.test.AbstractSubscriptionIT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

class AbsenceRequestSubscriptionIT : AbstractSubscriptionIT("absence/absence-request") {

    @Autowired
    private lateinit var absenceRequestService: AbsenceRequestService

    private fun create(userId: String = NonSecConfiguration.NON_SEC_USER_ID): AbsenceRequest {
        return absenceRequestService.create(AbsenceRequestInput("Test", userId, AbsenceType.VACATION, LocalDate.now()))
    }

    @Test
    fun createSubscription() {
        val updatedEntity = performGQLSubscription("AbsenceRequestUpdateSubscription", { create() }).get("$.data.absenceRequestUpdate", AbsenceRequestUpdateGQLResponse::class.java)
        assertThat(updatedEntity.type).isEqualTo(EntityUpdateType.CREATED)
        assertThat(updatedEntity.entity.description).isEqualTo("Test")
    }

    @Test
    fun differentUser() {
        performGQLSubscriptionNoResponse("AbsenceRequestUpdateSubscription", { create("123") })
    }
}