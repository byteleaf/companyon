package de.byteleaf.companyon.absence.logic

import de.byteleaf.companyon.absence.constant.ApprovedQueryState
import de.byteleaf.companyon.absence.dto.input.AbsenceRequestInput
import de.byteleaf.companyon.absence.dto.output.AbsenceRequest
import de.byteleaf.companyon.absence.dto.update.AbsenceRequestUpdate
import de.byteleaf.companyon.absence.entity.AbsenceRequestEntity
import de.byteleaf.companyon.absence.repository.AbsenceRequestQueryRepository
import de.byteleaf.companyon.absence.repository.AbsenceRequestRepository
import de.byteleaf.companyon.auth.logic.SecurityContextService
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.error.exception.InputValidationException
import de.byteleaf.companyon.common.logic.AbstractEventDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class AbsenceRequestService : AbstractEventDataService<AbsenceRequestEntity, AbsenceRequest, AbsenceRequestUpdate, AbsenceRequestInput, AbsenceRequestRepository>() {

    override fun getEntityType(): EntityType = EntityType.ABSENCE_REQUEST

    @Autowired
    private lateinit var securityContextService: SecurityContextService

    @Autowired
    private lateinit var absenceRequestQueryRepository: AbsenceRequestQueryRepository

    fun findAll(from: LocalDate?, to: LocalDate?, userIds: Collection<String>?, approved: ApprovedQueryState): List<AbsenceRequest> =
        absenceRequestQueryRepository.findAbsenceRequests(from, to, userIds, approved).map { entityToOutput(it) }

    override fun create(input: AbsenceRequestInput): AbsenceRequest {
        validateFromTo(input)
        return super.create(input)
    }

    /**
     * If the current user isn't a admin, the [AbsenceRequestEntity.approvedBy] will be reset
     */
    override fun update(id: String, input: AbsenceRequestInput): AbsenceRequest {
        validateFromTo(input)
        return super.update(id, input) {
            if (!securityContextService.getCurrentUser().admin) {
                it.approvedBy = null
            }
        }
    }

    private fun validateFromTo(input: AbsenceRequestInput) {
        if (input.from.isEqual(input.to) && input.absenceLastDayInMinutes != null) {
            throw InputValidationException(getEntityType(), "[from] and [to] (${DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(input.from)}) are one the same day, [absenceLastDayInMinutes] should not be defined")
        }
    }
}