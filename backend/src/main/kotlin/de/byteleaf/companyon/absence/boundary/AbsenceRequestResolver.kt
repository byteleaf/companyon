package de.byteleaf.companyon.absence.boundary

import de.byteleaf.companyon.absence.access.AbsenceRequestAccessService
import de.byteleaf.companyon.absence.constant.AbsenceType
import de.byteleaf.companyon.absence.constant.ApprovedQueryState
import de.byteleaf.companyon.absence.dto.input.AbsenceRequestInput
import de.byteleaf.companyon.absence.dto.output.AbsenceRequest
import de.byteleaf.companyon.absence.dto.update.AbsenceRequestUpdate
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import java.time.LocalDate

@Controller
class AbsenceRequestResolver : GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {

    @Autowired
    private lateinit var absenceRequestAccessService: AbsenceRequestAccessService

    fun getAbsenceRequests(from: LocalDate?, to: LocalDate?, userIds: Collection<String>?, types: Collection<AbsenceType>?, approved: ApprovedQueryState?): List<AbsenceRequest> =
        absenceRequestAccessService.findAll(from, to, userIds, types, approved ?: ApprovedQueryState.ALL)

    fun createAbsenceRequest(input: AbsenceRequestInput): AbsenceRequest = absenceRequestAccessService.create(input)

    fun deleteAbsenceRequest(id: String): AbsenceRequest = absenceRequestAccessService.delete(id)

    fun updateAbsenceRequest(id: String, input: AbsenceRequestInput): AbsenceRequest = absenceRequestAccessService.update(id, input)

    fun absenceRequestUpdate(): Publisher<AbsenceRequestUpdate> = absenceRequestAccessService.getPublisher()
}