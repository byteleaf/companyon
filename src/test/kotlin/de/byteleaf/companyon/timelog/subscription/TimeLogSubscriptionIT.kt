package de.byteleaf.companyon.timelog.subscription

import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.project.dto.TimeLogUpdateGQLResponse
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TimeLogSubscriptionIT : AbstractTimeLogSubscription() {

    @Test
    fun currentUser() {
        val projectUpdated = performGQLSubscription("TimeLogUpdateSubscription", { createTimeLog() })
            .get("$.data.timeLogUpdate", TimeLogUpdateGQLResponse::class.java)
        Assertions.assertThat(projectUpdated.type).isEqualTo(EntityUpdateType.CREATED)
        Assertions.assertThat(projectUpdated.entity.beakInMinutes).isEqualTo(15)
        Assertions.assertThat(projectUpdated.entity.user!!.firstName).isEqualTo("Jeff")
    }

    @Test
    fun differentUser() {
        performGQLSubscriptionNoResponse("TimeLogUpdateSubscription", { createTimeLog("123") })
    }
}