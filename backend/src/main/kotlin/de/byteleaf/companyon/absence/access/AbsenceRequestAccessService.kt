package de.byteleaf.companyon.absence.access

import de.byteleaf.companyon.absence.constant.ApprovedQueryState
import de.byteleaf.companyon.absence.dto.input.AbsenceRequestInput
import de.byteleaf.companyon.absence.dto.output.AbsenceRequest
import de.byteleaf.companyon.absence.dto.update.AbsenceRequestUpdate
import de.byteleaf.companyon.absence.logic.AbsenceRequestService
import de.byteleaf.companyon.auth.logic.SecurityContextService
import de.byteleaf.companyon.auth.permission.PermissionException
import de.byteleaf.companyon.auth.permission.PermissionType
import de.byteleaf.companyon.auth.permission.handler.AdminPermission
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class AbsenceRequestAccessService {

    @Autowired
    private lateinit var absenceRequestService: AbsenceRequestService

    @Autowired
    private lateinit var securityContextService: SecurityContextService

    @Autowired
    private lateinit var adminPermission: AdminPermission

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #userIds)")
    fun findAll(from: LocalDate?, to: LocalDate?, userIds: Collection<String>?, approved: ApprovedQueryState): List<AbsenceRequest> =
        absenceRequestService.findAll(from, to, userIds, approved)

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #input.user)")
    fun create(input: AbsenceRequestInput): AbsenceRequest {
        checkIfStartsInPast(input.from)
        return absenceRequestService.create(input)
    }

    fun delete(id: String): AbsenceRequest = delete(id, absenceRequestService.get(id).user)

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #userId)")
    private fun delete(id: String, @Suppress("UNUSED_PARAMETER") userId: String): AbsenceRequest {
        checkIfStartsInPast(absenceRequestService.get(id).from)
        return absenceRequestService.delete(id)
    }

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #input.user)")
    fun update(id: String, input: AbsenceRequestInput): AbsenceRequest {
        checkIfStartsInPast(absenceRequestService.get(id).from)
        checkIfStartsInPast(input.from)
        return absenceRequestService.update(id, input)
    }

    fun getPublisher(): Publisher<AbsenceRequestUpdate> = absenceRequestService.getPublisher { permissionHandler, event ->
        permissionHandler.hasPermission(PermissionType.CURRENT_USER_OR_ADMIN, event.entity!!.user, true)
    }

    /**
     * Ony admin users are allowed to modify [AbsenceRequest] which started in the past
     * @throws PermissionException
     */
    private fun checkIfStartsInPast(from: LocalDate) {
        if (from.isBefore(LocalDate.now()) && !securityContextService.getCurrentUser().admin)
            adminPermission.throwException("Only a admin users are allowed to create, modify or delete AbsenceRequests in the past")
    }
}