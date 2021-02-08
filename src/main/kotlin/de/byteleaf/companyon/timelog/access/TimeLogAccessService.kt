package de.byteleaf.companyon.timelog.access

import de.byteleaf.companyon.timelog.dto.TimeLog
import de.byteleaf.companyon.timelog.dto.TimeLogInput
import de.byteleaf.companyon.timelog.logic.TimeLogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class TimeLogAccessService {

    @Autowired
    private lateinit var timeLogService: TimeLogService

    // TODO isAdminOrCurrentUser
    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #userId)")
    fun findTimeLogs(from: OffsetDateTime?, to: OffsetDateTime?, userId: String?, projectId: String?): List<TimeLog> =
        timeLogService.findTimeLogs(from, to, userId, projectId)

    // TODO isAdminOrCurrentUser
    fun create(input: TimeLogInput): TimeLog = timeLogService.create(input)

    // TODO isAdminOrCurrentUser
    fun delete(id: String): TimeLog = timeLogService.delete(id)

    // TODO isAdminOrCurrentUser
    fun update(id: String, input: TimeLogInput): TimeLog = timeLogService.update(id, input)
}