package de.byteleaf.companyon.task.subscription

import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.task.dto.TaskUpdateGQLResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.context.TestPropertySource

@TestPropertySource(properties = ["app.non-sec-user-admin=true"])
class TaskSubscriptionAdminIT : AbstractTaskSubscription() {

    @Test
    fun differentUser() {
        val updatedEntity = performGQLSubscription("TaskUpdateSubscription", { createTask("123") }).get("$.data.taskUpdate", TaskUpdateGQLResponse::class.java)
        assertThat(updatedEntity.type).isEqualTo(EntityUpdateType.CREATED)
    }
}
