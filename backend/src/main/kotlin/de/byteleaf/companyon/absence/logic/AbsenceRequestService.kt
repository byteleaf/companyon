package de.byteleaf.companyon.absence.logic

import de.byteleaf.companyon.absence.constant.ApprovedQueryState
import de.byteleaf.companyon.absence.dto.input.AbsenceRequestInput
import de.byteleaf.companyon.absence.dto.output.AbsenceRequest
import de.byteleaf.companyon.absence.dto.update.AbsenceRequestUpdate
import de.byteleaf.companyon.absence.entity.AbsenceRequestEntity
import de.byteleaf.companyon.absence.repository.AbsenceRequestQueryRepository
import de.byteleaf.companyon.absence.repository.AbsenceRequestRepository
import de.byteleaf.companyon.auth.logic.SecurityContextService
import de.byteleaf.companyon.auth.permission.PermissionException
import de.byteleaf.companyon.auth.permission.PermissionType
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.logic.AbstractEventDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class AbsenceRequestService : AbstractEventDataService<AbsenceRequestEntity, AbsenceRequest, AbsenceRequestUpdate, AbsenceRequestInput, AbsenceRequestRepository>() {

    override fun getEntityType(): EntityType = EntityType.ABSENCE_REQUEST

    @Autowired
    private lateinit var securityContextService: SecurityContextService

    @Autowired
    private lateinit var absenceRequestQueryRepository: AbsenceRequestQueryRepository

    fun findAll(from: LocalDate?, to: LocalDate?, userIds: Collection<String>?, approved: ApprovedQueryState): List<AbsenceRequest> =
        absenceRequestQueryRepository.findAbsenceRequests(from, to, userIds, approved).map { entityToOutput(it) }

    override fun delete(id: String): AbsenceRequest {
        checkIfStartsInPast(id)
        return super.delete(id)
    }

    /**
     * If the current user isn't a admin, the [AbsenceRequestEntity.approvedBy] will be reset
     */
    override fun update(id: String, input: AbsenceRequestInput): AbsenceRequest {
        checkIfStartsInPast(id)
        return super.update(id, input) {
            if (!securityContextService.getCurrentUser().admin) {
                it.approvedBy = null
            }
        }
    }

    /**
     * Ony admin users are allowed to modify [AbsenceRequest] which started in the past
     * @throws PermissionException
     */
    private fun checkIfStartsInPast(absenceRequestId: String) {
        if (get(absenceRequestId).from.isAfter(LocalDate.now()) && !securityContextService.getCurrentUser().admin) throw PermissionException(
            PermissionType.ADMIN,
            "Only a admin users are allowed to modify or delete AbsenceRequests from the past"
        )
    }
}