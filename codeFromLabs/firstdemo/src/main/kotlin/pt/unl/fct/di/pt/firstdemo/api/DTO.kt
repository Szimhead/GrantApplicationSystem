package pt.unl.fct.di.pt.firstdemo.api

import pt.unl.fct.di.pt.firstdemo.services.*
import java.util.Date

data class UserDTO(val id: Long, val name: String, val email: String, val address: String) {
    constructor(rev: ReviewerDAO) : this(rev.id, rev.name, rev.email, rev.address)
    constructor(stud: StudentDAO) : this(stud.id, stud.name, stud.email, stud.address)
}

data class OrganizationDTO(val id: Long, val name: String, val contact: String) {
    constructor(inst: InstitutionDAO) : this(inst.id, inst.name, inst.contact)
    constructor(spon: SponsorDAO) : this(spon.id, spon.name, spon.contact)
}

data class GrantCallDTO(val id: Long, val title: String, val description: String, val funding: Double, val openDate: Date, val closeDate: Date) {
    constructor(gc: GrantCallDAO) : this(gc.id, gc.title, gc.description, gc.funding, gc.openDate, gc.closeDate)
}

data class ApplicationDTO(val id: Long, val submissionDate: Date, val status:Int, var studentId:Long) {
    constructor(app: ApplicationDAO) : this(app.id, app.submissionDate, app.status, app.student.id)
}

data class PanelDTO(val id: Long){
    constructor(panel: PanelDAO) : this(panel.id)
}

data class ReviewDTO(val id:Long, val isAccepted: Boolean, val comment: String, val reviewerId: Long) {
    constructor(review: ReviewDAO) : this(review.id, review.isAccepted, review.comment, review.reviewer.id)
}

data class DataItemDTO(val id: Long, val name: String, val datatype: String, val isMandatory: Boolean) {
    constructor(dItem: DataItemDAO) : this(dItem.id, dItem.name, dItem.dataType, dItem.isMandatory)
}

data class CVRequirementDTO(val id: Long, val name: String, val datatype: String, val isMandatory: Boolean) {
    constructor(req: CVRequirementDAO) : this(req.id, req.name, req.dataType, req.isMandatory)
}

data class AnswerDTO(val id: Long, val name: String, val value: String, val datatype: String, val dataItemId: Long) {
    constructor(answer: AnswerDAO) : this(answer.id, answer.name, answer.value, answer.dataType, answer.dataItem.id)
}

data class CVItemDTO(val id: Long, val name: String, val value: String, val datatype: String) {
    constructor(cvItem: CVItemDAO) : this(cvItem.id, cvItem.name, cvItem.value, cvItem.dataType)
}

data class CVDTO(val id: Long, val items: MutableSet<CVItemDTO>) {
    constructor(cv: CVDAO) : this(cv.id, cv.CVItems.map{CVItemDTO(it)}.toMutableSet())
}