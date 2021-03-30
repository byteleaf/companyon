package de.byteleaf.companyon.vacation.logic

import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.logic.AbstractEventDataService
import de.byteleaf.companyon.vacation.dto.input.UserVacationAdjustmentInput
import de.byteleaf.companyon.vacation.dto.output.UserVacationAdjustment
import de.byteleaf.companyon.vacation.dto.update.UserVacationAdjustmentUpdate
import de.byteleaf.companyon.vacation.entity.UserVacationAdjustmentEntity
import de.byteleaf.companyon.vacation.repository.UserVacationAdjustmentRepository
import org.springframework.stereotype.Service

@Service
class UserVacationAdjustmentService : AbstractEventDataService<UserVacationAdjustmentEntity, UserVacationAdjustment, UserVacationAdjustmentUpdate, UserVacationAdjustmentInput, UserVacationAdjustmentRepository>() {

    override fun getEntityType(): EntityType = EntityType.USER_VACATION_ADJUSTMENT
}