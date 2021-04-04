package de.byteleaf.companyon.absence.logic

import de.byteleaf.companyon.absence.dto.input.AbsenceRequestInput
import de.byteleaf.companyon.absence.dto.output.AbsenceRequest
import de.byteleaf.companyon.absence.dto.update.AbsenceRequestUpdate
import de.byteleaf.companyon.absence.entity.AbsenceRequestEntity
import de.byteleaf.companyon.absence.repository.AbsenceRequestRepository
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.logic.AbstractEventDataService
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class AbsenceRequestService : AbstractEventDataService<AbsenceRequestEntity, AbsenceRequest, AbsenceRequestUpdate, AbsenceRequestInput, AbsenceRequestRepository>() {

    override fun getEntityType(): EntityType = EntityType.ABSENCE_REQUEST


    // TODO if not set the default should be calculated by the default work hours per week of each employee

    fun findAll(from: OffsetDateTime?, to: OffsetDateTime?, userIds: Collection<String>?, approved: Boolean): List<AbsenceRequest> {
        // TODO
        return emptyList()
    }
}