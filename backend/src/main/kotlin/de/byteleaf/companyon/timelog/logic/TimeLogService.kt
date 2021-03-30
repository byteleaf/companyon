package de.byteleaf.companyon.timelog.logic

import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.logic.AbstractEventDataService
import de.byteleaf.companyon.timelog.dto.TimeLog
import de.byteleaf.companyon.timelog.dto.TimeLogInput
import de.byteleaf.companyon.timelog.dto.TimeLogUpdate
import de.byteleaf.companyon.timelog.entity.TimeLogEntity
import de.byteleaf.companyon.timelog.repository.TimeLogQueryRepository
import de.byteleaf.companyon.timelog.repository.TimeLogRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class TimeLogService : AbstractEventDataService<TimeLogEntity, TimeLog, TimeLogUpdate, TimeLogInput, TimeLogRepository>() {

    @Autowired
    private lateinit var timeLogQueryRepository: TimeLogQueryRepository

    override fun getEntityType(): EntityType = EntityType.TIME_LOG

    fun findTimeLogs(from: OffsetDateTime?, to: OffsetDateTime?, userId: String?, projectId: String?): List<TimeLog> =
        timeLogQueryRepository.findTimeLogs(from, to, userId, projectId).map { entityToOutput(it) }
}
