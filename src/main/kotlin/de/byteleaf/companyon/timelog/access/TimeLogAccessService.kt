package de.byteleaf.companyon.timelog.access

import de.byteleaf.companyon.project.dto.ProjectUpdate
import de.byteleaf.companyon.timelog.dto.TimeLog
import de.byteleaf.companyon.timelog.dto.TimeLogInput
import de.byteleaf.companyon.timelog.dto.TimeLogUpdate
import de.byteleaf.companyon.timelog.logic.TimeLogService
import org.reactivestreams.Publisher
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

    fun delete(id: String): TimeLog = delete(id, timeLogService.get(id).user)

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #userId)")
    private fun delete(id: String, userId: String): TimeLog = timeLogService.delete(id)

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #input.user)")
    fun update(id: String, input: TimeLogInput): TimeLog = update(id, input, timeLogService.get(id).user)

    /**
     * The current user must have the permission to for the new and old userId of the time log
     */
    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #currentOwnerId)")
    private fun update(id: String, input: TimeLogInput, currentOwnerId: String): TimeLog = timeLogService.update(id, input)


    fun getPublisher(): Publisher<TimeLogUpdate> = timeLogService.getPublisher()
}