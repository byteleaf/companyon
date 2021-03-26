package de.byteleaf.companyon.timelog

import de.byteleaf.companyon.auth.permission.PermissionType
import de.byteleaf.companyon.common.error.ErrorExtensionKey
import de.byteleaf.companyon.test.AbstractQueryMutationIT
import de.byteleaf.companyon.test.util.GQLErrorUtil
import org.junit.jupiter.api.Test

class TimeLogAccessDeniedIT : AbstractQueryMutationIT("time-log") {

    /**
     * You can only get the time logs from your own user, if you not admin
     */
    @Test
    fun getTimeLogsByAllUsers() {
        GQLErrorUtil.expectNoPermission(performGQL("GetTimeLogs", mapOf(), true), PermissionType.ADMIN)
    }

    @Test
    fun getTimeLogsFromOtherUser() {
        GQLErrorUtil.expectNoPermission(
            performGQL("GetTimeLogs", mapOf(Pair("userId", "other_user_id")), true),
            PermissionType.CURRENT_USER_OR_ADMIN, ErrorExtensionKey.TARGET_USER_ID, "other_user_id"
        )
    }
}
