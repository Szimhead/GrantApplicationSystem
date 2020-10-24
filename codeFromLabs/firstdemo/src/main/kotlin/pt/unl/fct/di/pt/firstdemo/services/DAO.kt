package pt.unl.fct.di.pt.firstdemo.services

import pt.unl.fct.di.pt.firstdemo.api.*
import java.util.*
import javax.persistence.*

@Entity
data class ApplicationDAO(
        @Id
        @GeneratedValue
        var id: Long,
        var submissionDate: Date,
        var status: Int,
        @ManyToOne
        var grantCall: GrantCallDAO,
        @OneToMany
        var reviews: MutableList<ReviewDAO>,
        @ManyToOne
        var student: StudentDAO,
        @OneToMany
        var answers: MutableList<AnswerDAO>
) {
    constructor() : this(0, Date(), 0, GrantCallDAO(), mutableListOf(), StudentDAO(), mutableListOf())
    constructor(app: ApplicationDTO) : this(app.id, app.submissionDate, app.status, GrantCallDAO(), mutableListOf(), StudentDAO(), mutableListOf())
}

@Entity
data class StudentDAO(
        @Id
        @GeneratedValue
        var id: Long,
        val name: String,
        val email: String,
        val address: String,
        @ManyToOne
        var institution: InstitutionDAO
) {
    constructor() : this(0, "name", "e-mail", "address", InstitutionDAO())
    constructor(stud: UserDTO) : this(stud.id, stud.name, stud.email, stud.address, InstitutionDAO())
}

@Entity
data class ReviewerDAO(
        @Id
        @GeneratedValue
        var id: Long,
        val name: String,
        val email: String,
        val address: String,
        @ManyToMany(mappedBy = "reviewers")
        var panels: MutableList<PanelDAO>,
        @ManyToOne
        var institution: InstitutionDAO,
        @OneToMany
        var reviews: MutableList<ReviewDAO>
) {
    constructor() : this(0, "name", "e-mail", "address", mutableListOf(),InstitutionDAO(), mutableListOf())
    constructor(rev: UserDTO) : this(rev.id, rev.name, rev.email, rev.address, mutableListOf() ,InstitutionDAO(), mutableListOf())
}

@Entity
data class GrantCallDAO(
        @Id
        @GeneratedValue
        var id: Long,
        var title: String,
        var description: String,
        var funding: Double,
        var openDate: Date,
        var closeDate: Date,
        @OneToMany
        var applications: List<ApplicationDAO>,
        @OneToOne
        var panel: PanelDAO,
        @ManyToMany
        var dataItems: List<DataItemDAO>
) {
    constructor() : this(0, "title", "description", 0.00, Date(), Date(), listOf<ApplicationDAO>(), PanelDAO(), listOf<DataItemDAO>())
    constructor(gc: GrantCallDTO) : this(0, gc.title, gc.description, gc.funding, gc.openDate, gc.closeDate, listOf<ApplicationDAO>(), PanelDAO(), listOf<DataItemDAO>())
}

@Entity
data class AnswerDAO(
        @Id
        @GeneratedValue
        var id: Long,
        val name: String,
        val value: String,
        val dataType: String
) {
    constructor() : this(0, "name", "value", "data type")
    constructor(answer: AnswerDTO) : this(answer.id, answer.name, answer.value, answer.datatype)
}

@Entity
data class CVItemDAO(
        @Id
        @GeneratedValue
        var id: Long,
        val name: String,
        val value: String,
        val dataType: String
) {
    constructor() : this(0, "name", "value", "data type")
}

@Entity
data class CVDAO(
        @Id
        @GeneratedValue
        var id: Long,
        @OneToMany
        val items: List<CVItemDAO>
) {
    constructor() : this(0, listOf<CVItemDAO>())
}

@Entity
data class CVRequirementDAO(
        @Id
        @GeneratedValue
        var id: Long,
        var name: String,
        var dataType: String,
        var isMandatory: Boolean
) {
    constructor() : this(0, "name", "data type", false)
    constructor(cvr: CVRequirementDTO) : this(0, cvr.name, cvr.datatype, cvr.isMandatory)
}

@Entity
data class DataItemDAO(
        @Id
        @GeneratedValue
        var id: Long,
        var name: String,
        var dataType: String,
        var isMandatory: Boolean,
        @OneToOne
        var grantCall: GrantCallDAO
) {
    constructor() : this(0, "name", "data type", false, GrantCallDAO())
    constructor(dItem: DataItemDTO) : this(0, dItem.name, dItem.datatype, dItem.isMandatory, GrantCallDAO())
}

@Entity
data class InstitutionDAO(
        @Id
        @GeneratedValue
        var id: Long,
        var name: String,
        var contact: String,
        @OneToMany
        var students: MutableList<StudentDAO>,
        @OneToMany
        var reviewers: MutableList<ReviewerDAO>
) {
    constructor() : this(0, "name", "contact", emptyList<StudentDAO>() as MutableList<StudentDAO>, emptyList<ReviewerDAO>() as MutableList<ReviewerDAO>)
    constructor(inst: OrganizationDTO) : this(inst.id, inst.name, inst.contact, emptyList<StudentDAO>() as MutableList<StudentDAO>, emptyList<ReviewerDAO>() as MutableList<ReviewerDAO>)

}

@Entity
data class SponsorDAO(
        @Id
        @GeneratedValue
        var id: Long,
        val name: String,
        val contact: String
) {
    constructor() : this(0, "name", "contact")
}

@Entity
data class PanelDAO(
        @Id
        @GeneratedValue
        var id: Long,
        @ManyToMany
        var reviewers: List<ReviewerDAO>,
        @OneToOne
        var grantCall: GrantCallDAO
) {
    constructor() : this(0, listOf<ReviewerDAO>(), GrantCallDAO())
    constructor(panel: PanelDTO) : this(panel.id, listOf<ReviewerDAO>(), GrantCallDAO())
}

@Entity
data class ReviewDAO(
        @Id
        @GeneratedValue
        var id: Long,
        val isAccepted: Boolean,
        val comment: String,
        @ManyToOne
        val application: ApplicationDAO,
        @ManyToOne
        val reviewer: ReviewerDAO
) {
    constructor() : this(0, false, "comment", ApplicationDAO(), ReviewerDAO())
    constructor(review: ReviewDTO) : this(review.id, review.isAccepted, review.comment, ApplicationDAO(), ReviewerDAO())
}