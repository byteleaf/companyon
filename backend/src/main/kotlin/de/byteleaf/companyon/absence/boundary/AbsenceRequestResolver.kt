package de.byteleaf.companyon.absence.boundary

import de.byteleaf.companyon.absence.access.AbsenceRequestAccessService
import de.byteleaf.companyon.absence.dto.input.AbsenceRequestInput
import de.byteleaf.companyon.absence.dto.output.AbsenceRequest
import de.byteleaf.companyon.absence.dto.update.AbsenceRequestUpdate
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import java.time.OffsetDateTime

@Controller
class AbsenceRequestResolver : GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {

    @Autowired
    private lateinit var vacationRequestAccessService: AbsenceRequestAccessService

    fun getVacationRequests(from: OffsetDateTime?, to: OffsetDateTime?, userIds: Collection<String>?, approved: Boolean = false): List<AbsenceRequest> =
        vacationRequestAccessService.findAll(from, to, userIds, approved)

    fun createVacationRequest(input: AbsenceRequestInput): AbsenceRequest = vacationRequestAccessService.create(input)

    fun deleteVacationRequest(id: String): AbsenceRequest = vacationRequestAccessService.delete(id)

    fun updateVacationRequest(id: String, input: AbsenceRequestInput): AbsenceRequest = vacationRequestAccessService.update(id, input)

    fun vacationRequestUpdate(): Publisher<AbsenceRequestUpdate> = vacationRequestAccessService.getPublisher()
}