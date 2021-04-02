package de.byteleaf.companyon.vacation.access

import de.byteleaf.companyon.vacation.dto.input.UserVacationAdjustmentInput
import de.byteleaf.companyon.vacation.dto.output.UserVacationAdjustment
import de.byteleaf.companyon.vacation.logic.UserVacationAdjustmentService
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class UserVacationAdjustmentAccessService {
    @Autowired
    private lateinit var userVacationAdjustmentService: UserVacationAdjustmentService

    // TODO check list
    fun findAll(from: OffsetDateTime?, to: OffsetDateTime?, userIds: Collection<String>?, approved: Boolean = false): List<UserVacationAdjustment> =
        userVacationAdjustmentService.findAll(from, to, userIds, approved)

    fun update(user: String, input: UserVacationAdjustmentInput): UserVacationAdjustment = userVacationAdjustmentService.update(user, input)

    fun getPublisher(): Publisher<UserVacationAdjustment> = userVacationAdjustmentService.getPublisher()
}