package de.byteleaf.companyon.project.dto

import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.project.entity.ProjectState

class ProjectGQLResponse(
    var id: String,
    var name: String,
    var state: ProjectState,
    var company: Company?
)