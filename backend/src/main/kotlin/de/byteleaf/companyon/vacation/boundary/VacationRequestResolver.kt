package de.byteleaf.companyon.vacation.boundary

import de.byteleaf.companyon.timelog.dto.TimeLog
import de.byteleaf.companyon.vacation.access.VacationRequestAccessService
import de.byteleaf.companyon.vacation.dto.input.VacationRequestInput
import de.byteleaf.companyon.vacation.dto.output.VacationRequest
import de.byteleaf.companyon.vacation.dto.update.VacationRequestUpdate
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import java.time.OffsetDateTime

@Controller
class VacationRequestResolver : GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {

    @Autowired
    private lateinit var vacationRequestAccessService: VacationRequestAccessService

    fun getVacationRequests(from: OffsetDateTime?, to: OffsetDateTime?, userIds: Collection<String>?, approved: Boolean = false): List<VacationRequest> =
        vacationRequestAccessService.getVacationRequests(from, to, userIds, approved)

    fun createVacationRequest(input: VacationRequestInput): TimeLog = vacationRequestAccessService.create(input)

    fun deleteVacationRequest(id: String): TimeLog = vacationRequestAccessService.delete(id)

    fun updateVacationRequest(id: String, input: VacationRequestInput): TimeLog = vacationRequestAccessService.update(id, input)

    fun vacationRequestUpdate(): Publisher<VacationRequestUpdate> = vacationRequestAccessService.getPublisher()
}