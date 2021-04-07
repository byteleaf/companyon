package de.byteleaf.companyon.absence.boundary

import de.byteleaf.companyon.absence.access.AbsenceRequestAccessService
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
import org.springframework.validation.annotation.Validated
import java.time.LocalDate
import javax.validation.Valid

@Validated
@Controller
class AbsenceRequestResolver : GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {

    @Autowired
    private lateinit var absenceRequestAccessService: AbsenceRequestAccessService

    fun getAbsenceRequests(from: LocalDate?, to: LocalDate?, userIds: Collection<String>?, approved: ApprovedQueryState = ApprovedQueryState.ALL): List<AbsenceRequest> =
        absenceRequestAccessService.findAll(from, to, userIds, approved)

    fun createAbsenceRequest(@Valid input: AbsenceRequestInput): AbsenceRequest = absenceRequestAccessService.create(input)

    fun deleteAbsenceRequest(id: String): AbsenceRequest = absenceRequestAccessService.delete(id)

    fun updateAbsenceRequest(id: String, @Valid input: AbsenceRequestInput): AbsenceRequest = absenceRequestAccessService.update(id, input)

    fun absenceRequestUpdate(): Publisher<AbsenceRequestUpdate> = absenceRequestAccessService.getPublisher()
}