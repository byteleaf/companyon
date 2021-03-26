package de.byteleaf.companyon.task

import de.byteleaf.companyon.auth.permission.PermissionType
import de.byteleaf.companyon.common.error.ErrorExtensionKey
import de.byteleaf.companyon.test.AbstractQueryMutationIT
import de.byteleaf.companyon.test.util.GQLErrorUtil
import org.junit.jupiter.api.Test

class TaskAccessDeniedIT : AbstractQueryMutationIT("task") {

    /**
     * You can only get the tasks from your own user, if you not admin
     */
    @Test
    fun getTasksByAllUsers() {
        GQLErrorUtil.expectNoPermission(performGQL("GetTasks", mapOf(), true), PermissionType.ADMIN)
    }

    @Test
    fun getTasksFromOtherUser() {
        GQLErrorUtil.expectNoPermission(
            performGQL("GetTasks", mapOf(Pair("user", "other_user_id")), true),
            PermissionType.CURRENT_USER_OR_ADMIN, ErrorExtensionKey.TARGET_USER_ID, "other_user_id"
        )
    }
}
