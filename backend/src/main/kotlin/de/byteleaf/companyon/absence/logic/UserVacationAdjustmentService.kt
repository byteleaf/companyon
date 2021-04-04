package de.byteleaf.companyon.absence.logic

import de.byteleaf.companyon.absence.dto.input.UserVacationAdjustmentInput
import de.byteleaf.companyon.absence.dto.output.UserVacationAdjustment
import de.byteleaf.companyon.absence.dto.update.UserVacationAdjustmentUpdate
import de.byteleaf.companyon.absence.entity.UserVacationAdjustmentEntity
import de.byteleaf.companyon.absence.repository.UserVacationAdjustmentRepository
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.logic.AbstractEventDataService
import org.springframework.stereotype.Service

@Service
class UserVacationAdjustmentService : AbstractEventDataService<UserVacationAdjustmentEntity, UserVacationAdjustment, UserVacationAdjustmentUpdate, UserVacationAdjustmentInput, UserVacationAdjustmentRepository>() {

    override fun getEntityType(): EntityType = EntityType.USER_VACATION_ADJUSTMENT

    fun findAll(userIds: Collection<String>?): List<UserVacationAdjustment> =
        if (userIds == null || userIds.isEmpty()) findAll() else repository.findByUserIn(userIds).map { entityToOutput(it) }
}