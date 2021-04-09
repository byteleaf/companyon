package de.byteleaf.companyon.timelog.subscription

import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.project.dto.TimeLogUpdateGQLResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.context.TestPropertySource

@TestPropertySource(properties = ["app.non-sec-user-admin=true"])
class TimeLogSubscriptionAdminIT : AbstractTimeLogSubscription() {

    @Test
    fun differentUser() {
        val updatedEntity = performGQLSubscription("TimeLogUpdateSubscription", { createTimeLog("123") }).get("$.data.timeLogUpdate", TimeLogUpdateGQLResponse::class.java)
        assertThat(updatedEntity.type).isEqualTo(EntityUpdateType.CREATED)
    }
}
