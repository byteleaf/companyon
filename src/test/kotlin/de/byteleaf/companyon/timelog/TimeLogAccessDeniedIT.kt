package de.byteleaf.companyon.timelog

import de.byteleaf.companyon.common.error.ErrorCode
import de.byteleaf.companyon.test.AbstractIT
import de.byteleaf.companyon.test.util.GQLErrorUtil
import org.junit.jupiter.api.Test

class TimeLogAccessDeniedIT: AbstractIT("time-log") {

    /**
     * You can only get the time logs from your own user, if you not admin
     */
    @Test
    fun getTimeLogsByAllUsers() {
        // TODO real error code!!!
        GQLErrorUtil.expectError(performGQL("GetTimeLogs", mapOf(), true), ErrorCode.ACCESS_DENIED_NO_ADMIN)
    }
}