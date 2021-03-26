package de.byteleaf.companyon.task.access

import de.byteleaf.companyon.auth.permission.PermissionType
import de.byteleaf.companyon.task.dto.Task
import de.byteleaf.companyon.task.dto.TaskInput
import de.byteleaf.companyon.task.dto.TaskUpdate
import de.byteleaf.companyon.task.entity.TaskState
import de.byteleaf.companyon.task.logic.TaskService
import java.util.*
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class TaskAccessService {

        @Autowired private lateinit var taskService: TaskService

        @PostAuthorize(
                        "hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, returnObject.user)")
        fun get(id: String): Task = taskService.get(id)

        @PostAuthorize(
                        "hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, returnObject.user)")
        fun getNullable(id: String?): Optional<Task> = taskService.getNullable(id)

        @PreAuthorize(
                        "hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #user)")
        fun findAll(user: String?, status: TaskState?): List<Task> =
                        taskService.findTasks(user, status)

        @PreAuthorize(
                        "hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #input.user)")
        fun create(input: TaskInput): Task = taskService.create(input)

        @PreAuthorize(
                        "hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #input.user)")
        fun update(id: String, input: TaskInput): Task = taskService.update(id, input)

        fun delete(id: String): Task = deleteWithUser(id, taskService.get(id).user)

        @PreAuthorize(
            "hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).CURRENT_USER_OR_ADMIN, #userId)")
        private fun deleteWithUser(id: String, userId: String): Task = taskService.delete(id)

        fun getPublisher(): Publisher<TaskUpdate> =
                        taskService.getPublisher { permissionHandler, event ->
                                permissionHandler.hasPermission(
                                                PermissionType.CURRENT_USER_OR_ADMIN,
                                                event.entity!!.user,
                                                true)
                        }
}
