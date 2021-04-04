package de.byteleaf.companyon.absence.access

import de.byteleaf.companyon.absence.dto.input.AbsenceRequestInput
import de.byteleaf.companyon.absence.dto.output.AbsenceRequest
import de.byteleaf.companyon.absence.dto.update.AbsenceRequestUpdate
import de.byteleaf.companyon.absence.logic.AbsenceRequestService
import de.byteleaf.companyon.auth.permission.PermissionType
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class AbsenceRequestAccessService {

    @Autowired
    private lateinit var vacationRequestService: AbsenceRequestService

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #userIds)")
    fun findAll(from: OffsetDateTime?, to: OffsetDateTime?, userIds: Collection<String>?, approved: Boolean = false): List<AbsenceRequest> =
        vacationRequestService.findAll(from, to, userIds, approved)

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #input.user)")
    fun create(input: AbsenceRequestInput): AbsenceRequest = vacationRequestService.create(input)

    fun delete(id: String): AbsenceRequest = vacationRequestService.delete(id)

    @PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #userId)")
    private fun delete(id: String, @Suppress("UNUSED_PARAMETER") userId: String): AbsenceRequest = vacationRequestService.delete(id)

    // verganegen nur admin start zeitpunkt past,  approved muss reseted werden

    fun update(id: String, input: AbsenceRequestInput): AbsenceRequest = vacationRequestService.update(id, input)

    fun getPublisher(): Publisher<AbsenceRequestUpdate> = vacationRequestService.getPublisher { permissionHandler, event ->
        permissionHandler.hasPermission(PermissionType.CURRENT_USER_OR_ADMIN, event.entity!!.user, true)
    }
}