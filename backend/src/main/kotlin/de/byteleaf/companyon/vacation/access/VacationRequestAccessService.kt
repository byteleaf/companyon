package de.byteleaf.companyon.vacation.access

import de.byteleaf.companyon.vacation.dto.input.VacationRequestInput
import de.byteleaf.companyon.vacation.dto.output.VacationRequest
import de.byteleaf.companyon.vacation.dto.update.VacationRequestUpdate
import de.byteleaf.companyon.vacation.logic.VacationRequestService
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class VacationRequestAccessService {

    @Autowired
    private lateinit var vacationRequestService: VacationRequestService

    // TODO Security
// TODO NullOrAdmin access right
// TODO IsUserIdInList access right
// TODO List off access rights
    fun findVacationRequests(from: OffsetDateTime?, to: OffsetDateTime?, userIds: Collection<String>?, approved: Boolean = false): List<VacationRequest> =
        vacationRequestService.findVacationRequests(from, to, userIds, approved)

    fun createVacationRequest(input: VacationRequestInput): VacationRequest = vacationRequestService.create(input)

    fun deleteVacationRequest(id: String): VacationRequest = vacationRequestService.delete(id)

    fun updateVacationRequest(id: String, input: VacationRequestInput): VacationRequest = vacationRequestService.update(id, input)

    fun vacationRequestUpdate(): Publisher<VacationRequestUpdate> = vacationRequestService.getPublisher()
}