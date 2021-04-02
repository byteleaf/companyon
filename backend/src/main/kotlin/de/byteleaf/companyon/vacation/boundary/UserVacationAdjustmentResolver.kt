package de.byteleaf.companyon.vacation.boundary

import de.byteleaf.companyon.timelog.dto.TimeLog
import de.byteleaf.companyon.vacation.access.UserVacationAdjustmentAccessService
import de.byteleaf.companyon.vacation.dto.input.UserVacationAdjustmentInput
import de.byteleaf.companyon.vacation.dto.output.UserVacationAdjustment
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

    fun updateUserVacationAdjustment(user: String, input: UserVacationAdjustmentInput): TimeLog = userVacationAdjustmentAccessService.update(user, input)

    fun userVacationAdjustmentUpdate(): Publisher<UserVacationAdjustment> = userVacationAdjustmentAccessService.getPublisher()
}