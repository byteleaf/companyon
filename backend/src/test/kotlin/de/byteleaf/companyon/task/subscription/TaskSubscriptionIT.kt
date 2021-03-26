package de.byteleaf.companyon.task.subscription

import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.task.dto.TaskUpdateGQLResponse
import de.byteleaf.companyon.test.AbstractSubscriptionIT
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TaskSubscriptionIT : AbstractTaskSubscription() {

    @Test
    fun currentUser() {
        val updatedEntity = performGQLSubscription("TaskUpdateSubscription", { createTask() })
            .get("$.data.taskUpdate", TaskUpdateGQLResponse::class.java)
        Assertions.assertThat(updatedEntity.type).isEqualTo(EntityUpdateType.CREATED)
        Assertions.assertThat(updatedEntity.entity.description).isEqualTo("test task")
    }

    @Test
    fun differentUser() {
        performGQLSubscriptionNoResponse("TaskUpdateSubscription", { createTask("123") })
    }
}