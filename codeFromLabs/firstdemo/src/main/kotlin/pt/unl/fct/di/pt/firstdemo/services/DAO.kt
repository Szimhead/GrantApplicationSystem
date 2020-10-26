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
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var reviews: MutableList<ReviewDAO>,
        @ManyToOne
        var student: StudentDAO,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var answers: MutableList<AnswerDAO>
) {
    constructor() : this(0, Date(), 0, GrantCallDAO(), mutableListOf<ReviewDAO>(), StudentDAO(), mutableListOf<AnswerDAO>())
    constructor(app: ApplicationDTO) : this(app.id, app.submissionDate, app.status, GrantCallDAO(), mutableListOf<ReviewDAO>(), StudentDAO(), mutableListOf<AnswerDAO>())
}

@Entity
data class StudentDAO(
        @Id
        @GeneratedValue
        var id: Long,
        var name: String,
        var email: String,
        var address: String,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var applications: MutableSet<ApplicationDAO>,
        @ManyToOne(fetch = FetchType.EAGER)
        var institution: InstitutionDAO,
        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var cv: CVDAO?
) {
    constructor() : this(0, "name", "e-mail", "address", mutableSetOf<ApplicationDAO>(), InstitutionDAO(), null)
    constructor(stud: UserDTO) : this(stud.id, stud.name, stud.email, stud.address, mutableSetOf<ApplicationDAO>(), InstitutionDAO(), null)

    override fun toString(): String {
        var apps = "["
        var first = true;
        for(app in applications) {
            if(!first)
                apps += ", "
            apps += app.id;

            first = false;
        }
        apps += "]"

        var institutionId = institution.id

        return "StudentDAO=(id: $id, name: $name, address: $address, applications: $apps, institution: $institutionId, cv: $cv)"
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is StudentDAO) return false;

        val a = this.id == other.id
        val b = this.name == other.name
        val c = this.address == other.address
        val d = this.applications == other.applications
        val e = this.institution.id == other.institution.id &&
                this.institution.name == other.institution.name &&
                this.institution.contact == other.institution.contact//compare institutions by id so it doesn't loop
        val f = this.cv == other.cv

        return a && b && c && d && e && f
    }
}

@Entity
data class ReviewerDAO(
        @Id
        @GeneratedValue
        var id: Long,
        var name: String,
        var email: String,
        var address: String,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var panelsInCharge: MutableList<PanelDAO>,
        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var panels: MutableList<PanelDAO>,
        @ManyToOne
        var institution: InstitutionDAO,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var reviews: MutableList<ReviewDAO>
) {
    constructor() : this(0, "name", "e-mail", "address", mutableListOf<PanelDAO>(), mutableListOf<PanelDAO>(),InstitutionDAO(), mutableListOf<ReviewDAO>())
    constructor(rev: UserDTO) : this(rev.id, rev.name, rev.email, rev.address, mutableListOf<PanelDAO>(), mutableListOf<PanelDAO>(),InstitutionDAO(), mutableListOf<ReviewDAO>())
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
        @OneToMany(mappedBy = "grantCall", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var applications: MutableSet<ApplicationDAO>,
        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var panel: PanelDAO?,
        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var dataItems: Set<DataItemDAO>
) {
    constructor() : this(0, "title", "description", 0.00, Date(), Date(), mutableSetOf<ApplicationDAO>(), null, setOf<DataItemDAO>())
    constructor(gc: GrantCallDTO) : this(0, gc.title, gc.description, gc.funding, gc.openDate, gc.closeDate, mutableSetOf<ApplicationDAO>(), null, setOf<DataItemDAO>())

}

@Entity
data class AnswerDAO(
        @Id
        @GeneratedValue
        var id: Long,
        var name: String,
        var value: String,
        var dataType: String,
        @ManyToOne
        var dataItem: DataItemDAO,
        @ManyToOne
        var application: ApplicationDAO
) {
    constructor() : this(0, "name", "value", "data type", DataItemDAO(), ApplicationDAO())
    constructor(answer: AnswerDTO) : this(answer.id, answer.name, answer.value, answer.datatype, DataItemDAO(), ApplicationDAO())
}

@Entity
data class CVItemDAO(
        @Id
        @GeneratedValue
        var id: Long,
        val name: String,
        val value: String,
        val dataType: String,
        @ManyToOne
        var CVRequirement: CVRequirementDAO,
        @ManyToOne
        var CV: CVDAO
) {
    constructor() : this(0, "name", "value", "data type", CVRequirementDAO(), CVDAO())
}

@Entity
data class CVDAO(
        @Id
        @GeneratedValue
        var id: Long,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        val CVItems: MutableList<CVItemDAO>,
        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var student: StudentDAO?
) {
    constructor() : this(0, mutableListOf<CVItemDAO>(), null)
}

@Entity
data class CVRequirementDAO(
        @Id
        @GeneratedValue
        var id: Long,
        var name: String,
        var dataType: String,
        var isMandatory: Boolean,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var CVItems: MutableList<CVItemDAO>
) {
    constructor() : this(0, "name", "data type", false,  mutableListOf<CVItemDAO>())
    constructor(cvr: CVRequirementDTO) : this(0, cvr.name, cvr.datatype, cvr.isMandatory, mutableListOf<CVItemDAO>())
}

@Entity
data class DataItemDAO(
        @Id
        @GeneratedValue
        var id: Long,
        var name: String,
        var dataType: String,
        var isMandatory: Boolean,
        @ManyToMany(mappedBy = "dataItems", fetch = FetchType.EAGER)
        var grantCalls: MutableList<GrantCallDAO>,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var answers: MutableList<AnswerDAO>
) {
    constructor() : this(0, "name", "data type", false, mutableListOf<GrantCallDAO>(), mutableListOf<AnswerDAO>())
    constructor(dItem: DataItemDTO) : this(0, dItem.name, dItem.datatype, dItem.isMandatory, mutableListOf<GrantCallDAO>(), mutableListOf<AnswerDAO>())
}

@Entity
data class InstitutionDAO(
        @Id
        @GeneratedValue
        var id: Long,
        var name: String,
        var contact: String,
        @OneToMany(mappedBy = "institution", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var students: MutableSet<StudentDAO>,
        @OneToMany(mappedBy = "institution", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var reviewers: MutableSet<ReviewerDAO>
) {
    constructor() : this(0, "name", "contact", mutableSetOf<StudentDAO>(), mutableSetOf<ReviewerDAO>())
    constructor(inst: OrganizationDTO) : this(inst.id, inst.name, inst.contact, mutableSetOf<StudentDAO>(), mutableSetOf<ReviewerDAO>())
}

@Entity
data class SponsorDAO(
        @Id
        @GeneratedValue
        var id: Long,
        var name: String,
        var contact: String,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var grantCalls: MutableList<GrantCallDAO>
) {
    constructor() : this(0, "name", "contact", mutableListOf<GrantCallDAO>())
    constructor(sponsor: OrganizationDTO) : this(sponsor.id, sponsor.name, sponsor.contact, mutableListOf<GrantCallDAO>())
}

@Entity
data class PanelDAO(
        @Id
        @GeneratedValue
        var id: Long,
        @ManyToOne
        var chair: ReviewerDAO?,
        @ManyToMany(mappedBy = "panels", fetch = FetchType.EAGER)
        var reviewers: List<ReviewerDAO>,
        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var grantCall: GrantCallDAO?
) {
    constructor() : this(0, null, listOf<ReviewerDAO>(), null)
    constructor(panel: PanelDTO) : this(panel.id, ReviewerDAO(), listOf<ReviewerDAO>(), GrantCallDAO()) //chair is taken from dto or created here?
    // the second solution is problematic, as the reviewer probably already exists in the system, but needs to be associated with the right panel
}

@Entity
data class ReviewDAO(
        @Id
        @GeneratedValue
        var id: Long,
        val isAccepted: Boolean,
        val comment: String,
        @ManyToOne
        var application: ApplicationDAO,
        @ManyToOne
        var reviewer: ReviewerDAO
) {
    constructor() : this(0, false, "comment", ApplicationDAO(), ReviewerDAO())
    constructor(review: ReviewDTO) : this(review.id, review.isAccepted, review.comment, ApplicationDAO(), ReviewerDAO())
}