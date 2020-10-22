package pt.unl.fct.di.pt.firstdemo.api

import pt.unl.fct.di.pt.firstdemo.services.*
import java.util.Date

data class UserDTO(val id: Long, val name: String, val email: String, val address: String) {
    constructor(rev: ReviewerDAO) : this(rev.id, rev.name, rev.email, rev.address)
}

data class OrganizationDTO(val id: Long, val name: String, val contact: String) {
    constructor(inst: InstitutionDAO) : this(inst.id, inst.name, inst.contact)
}

data class GrantCallDTO(val title: String, val description: String, val funding: Double, val openDate: Date, val closeDate: Date) {
    constructor(gc: GrantCallDAO) : this(gc.title, gc.description, gc.funding, gc.openDate, gc.closeDate)
}

data class ApplicationDTO(val id: Long, val submissionDate: Date, val status:Int) {
    constructor(app: ApplicationDAO) : this(app.id, app.submissionDate, app.status)
}

data class PanelDTO(val id: Long){
    constructor(panel: PanelDAO) : this(panel.id)
}

data class ReviewDTO(val id:Long, val isAccepted: Boolean, val comment: String)

data class DataItemDTO(val name: String, val datatype: String, val isMandatory: Boolean) {
    constructor(dItem: DataItemDAO) : this(dItem.name, dItem.dataType, dItem.isMandatory)
}

data class CVRequirementDTO(val name: String, val datatype: String, val isMandatory: Boolean) {
    constructor(req: CVRequirementDAO) : this(req.name, req.dataType, req.isMandatory)
}

data class AnswerDTO(val name: String, val value: String, val datatype: String)

data class CVItemDTO(val name: String, val value: String, val datatype: String)

data class CVDTO(val items: List<CVItemDTO>)