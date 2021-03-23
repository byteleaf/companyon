package de.byteleaf.companyon.project.dto

import de.byteleaf.companyon.project.entity.ProjectState

data class ProjectInput(val name: String, val company: String, val state: ProjectState = ProjectState.PLANNED)
