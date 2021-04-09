package de.byteleaf.companyon.task.subscription

import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.task.dto.TaskUpdateGQLResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TaskSubscriptionIT : AbstractTaskSubscription() {

    @Test
    fun currentUser() {
        val updatedEntity = performGQLSubscription("TaskUpdateSubscription", { createTask() }).get("$.data.taskUpdate", TaskUpdateGQLResponse::class.java)
        assertThat(updatedEntity.type).isEqualTo(EntityUpdateType.CREATED)
        assertThat(updatedEntity.entity.description).isEqualTo("test task")
    }

    @Test
    fun differentUser() {
        performGQLSubscriptionNoResponse("TaskUpdateSubscription", { createTask("123") })
    }
}
