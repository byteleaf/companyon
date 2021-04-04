package de.byteleaf.companyon.absence.access

import de.byteleaf.companyon.absence.dto.input.UserVacationAdjustmentInput
import de.byteleaf.companyon.absence.dto.output.UserVacationAdjustment
import de.byteleaf.companyon.absence.dto.update.UserVacationAdjustmentUpdate
import de.byteleaf.companyon.absence.logic.UserVacationAdjustmentService
import de.byteleaf.companyon.auth.annotation.IsAdmin
import de.byteleaf.companyon.auth.permission.PermissionType
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class UserVacationAdjustmentAccessService {

    @Autowired
    private lateinit var userVacationAdjustmentService: UserVacationAdjustmentService

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #userIds)")
    fun findAll(userIds: Collection<String>?): List<UserVacationAdjustment> = userVacationAdjustmentService.findAll(userIds)

    @IsAdmin
    fun update(user: String, input: UserVacationAdjustmentInput): UserVacationAdjustment = userVacationAdjustmentService.update(user, input)

    fun getPublisher(): Publisher<UserVacationAdjustmentUpdate> = userVacationAdjustmentService.getPublisher { permissionHandler, event ->
        permissionHandler.hasPermission(PermissionType.CURRENT_USER_OR_ADMIN, event.entity!!.user, true)
    }
}