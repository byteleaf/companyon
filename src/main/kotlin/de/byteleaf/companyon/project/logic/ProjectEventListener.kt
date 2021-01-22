package de.byteleaf.companyon.project.logic

import de.byteleaf.companyon.common.event.EntityDeletedEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class ProjectEventListener {

    @Autowired
    private lateinit var projectService: ProjectService

    /**
     * If a company was deleted, all projects assigned to this company have to be deleted too!
     *
     * Hint: If a subscription is listening on deleted projects, its not possible to resolve the company of the project,
     * because it was already deleted!
     */
    @EventListener(condition = "#event.entityType == T(de.byteleaf.companyon.common.entity.EntityType).COMPANY")
    fun onCompanyDeleted(event: EntityDeletedEvent<*>) {
        projectService.deleteByCompany(event.entity.id!!)
    }
}