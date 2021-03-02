package de.byteleaf.companyon.timelog.subscription

import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.project.dto.TimeLogUpdateGQLResponse
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TimeLogSubscriptionIT : AbstractTimeLogSubscription() {

    @Test
    fun currentUser() {
        val updatedEntity = performGQLSubscription("TimeLogUpdateSubscription", { createTimeLog() })
            .get("$.data.timeLogUpdate", TimeLogUpdateGQLResponse::class.java)
        Assertions.assertThat(updatedEntity.type).isEqualTo(EntityUpdateType.CREATED)
        Assertions.assertThat(updatedEntity.entity.breakInMinutes).isEqualTo(15)
        Assertions.assertThat(updatedEntity.entity.user!!.firstName).isEqualTo("Jeff")
    }

    @Test
    fun differentUser() {
        performGQLSubscriptionNoResponse("TimeLogUpdateSubscription", { createTimeLog("123") })
    }
}