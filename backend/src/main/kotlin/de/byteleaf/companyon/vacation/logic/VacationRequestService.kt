package de.byteleaf.companyon.vacation.logic

import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.logic.AbstractEventDataService
import de.byteleaf.companyon.vacation.dto.input.VacationRequestInput
import de.byteleaf.companyon.vacation.dto.output.VacationRequest
import de.byteleaf.companyon.vacation.dto.update.VacationRequestUpdate
import de.byteleaf.companyon.vacation.entity.VacationRequestEntity
import de.byteleaf.companyon.vacation.repository.VacationRequestRepository
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class VacationRequestService : AbstractEventDataService<VacationRequestEntity, VacationRequest, VacationRequestUpdate, VacationRequestInput, VacationRequestRepository>() {

    override fun getEntityType(): EntityType = EntityType.VACATION_REQUEST

    fun findVacationRequests(from: OffsetDateTime?, to: OffsetDateTime?, userIds: Collection<String>?, approved: Boolean): List<VacationRequest> {
        // TODO
    }
}