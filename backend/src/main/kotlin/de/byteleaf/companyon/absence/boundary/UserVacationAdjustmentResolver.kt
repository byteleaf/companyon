package de.byteleaf.companyon.absence.boundary

import de.byteleaf.companyon.absence.access.UserVacationAdjustmentAccessService
import de.byteleaf.companyon.absence.dto.input.UserVacationAdjustmentInput
import de.byteleaf.companyon.absence.dto.output.UserVacationAdjustment
import de.byteleaf.companyon.absence.dto.update.UserVacationAdjustmentUpdate
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class UserVacationAdjustmentResolver : GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {

    @Autowired
    private lateinit var userVacationAdjustmentAccessService: UserVacationAdjustmentAccessService

    fun getUserVacationAdjustments(userIds: Collection<String>?): List<UserVacationAdjustment> = userVacationAdjustmentAccessService.findAll(userIds)

    fun updateUserVacationAdjustment(user: String, input: UserVacationAdjustmentInput): UserVacationAdjustment = userVacationAdjustmentAccessService.update(user, input)

    fun userVacationAdjustmentUpdate(): Publisher<UserVacationAdjustmentUpdate> = userVacationAdjustmentAccessService.getPublisher()
}