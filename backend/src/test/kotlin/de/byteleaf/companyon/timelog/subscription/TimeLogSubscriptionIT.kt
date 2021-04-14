package de.byteleaf.companyon.timelog.subscription

import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.project.dto.TimeLogUpdateGQLResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TimeLogSubscriptionIT : AbstractTimeLogSubscription() {

    @Test
    fun currentUser() {
        val updatedEntity = performGQLSubscription("TimeLogUpdateSubscription", { createTimeLog() })
            .get("$.data.timeLogUpdate", TimeLogUpdateGQLResponse::class.java)
        assertThat(updatedEntity.type).isEqualTo(EntityUpdateType.CREATED)
        assertThat(updatedEntity.entity.breakInMinutes).isEqualTo(15)
    }

    @Test
    fun differentUser() {
        performGQLSubscriptionNoResponse("TimeLogUpdateSubscription", { createTimeLog("123") })
    }
}
