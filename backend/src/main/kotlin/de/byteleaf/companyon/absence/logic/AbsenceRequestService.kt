package de.byteleaf.companyon.absence.logic

import de.byteleaf.companyon.absence.constant.AbsenceType
import de.byteleaf.companyon.absence.constant.ApprovedQueryState
import de.byteleaf.companyon.absence.dto.input.AbsenceRequestInput
import de.byteleaf.companyon.absence.dto.output.AbsenceRequest
import de.byteleaf.companyon.absence.dto.update.AbsenceRequestUpdate
import de.byteleaf.companyon.absence.entity.AbsenceRequestEntity
import de.byteleaf.companyon.absence.repository.AbsenceRequestQueryRepository
import de.byteleaf.companyon.absence.repository.AbsenceRequestRepository
import de.byteleaf.companyon.auth.logic.SecurityContextService
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

    fun findAll(from: LocalDate?, to: LocalDate?, userIds: Collection<String>?, types: Collection<AbsenceType>?, approved: ApprovedQueryState): List<AbsenceRequest> =
        absenceRequestQueryRepository.findAbsenceRequests(from, to, userIds, types, approved).map { entityToOutput(it) }

    /**
     * If the current user isn't a admin, the [AbsenceRequestEntity.approvedBy] will be reset
     */
    override fun update(id: String, input: AbsenceRequestInput): AbsenceRequest {
        return super.update(id, input) {
            if (!securityContextService.getCurrentUser().admin) {
                // TODO approve
                // it.approvedBy = null
            }
        }
    }
}