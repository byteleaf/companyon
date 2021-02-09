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

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #userId)")
    fun findTimeLogs(from: OffsetDateTime?, to: OffsetDateTime?, userId: String?, projectId: String?): List<TimeLog> =
        timeLogService.findTimeLogs(from, to, userId, projectId)

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #input.user)")
    fun create(input: TimeLogInput): TimeLog = timeLogService.create(input)

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #id)")
    fun delete(id: String): TimeLog = timeLogService.delete(id)

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #id)")
    fun update(id: String, input: TimeLogInput): TimeLog = timeLogService.update(id, input)
}