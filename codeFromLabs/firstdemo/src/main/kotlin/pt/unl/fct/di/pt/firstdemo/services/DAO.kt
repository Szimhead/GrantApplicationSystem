package pt.unl.fct.di.pt.firstdemo.services

import pt.unl.fct.di.pt.firstdemo.api.*
import java.util.*
import javax.persistence.*

@Entity
data class ApplicationDAO (
        @Id
        @GeneratedValue
        var id: Long,
        var submissionDate: Date,
        var status: Int,
        @ManyToOne(fetch = FetchType.EAGER)
        var grantCall: GrantCallDAO,
        @OneToMany(mappedBy = "application", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var reviews: MutableSet<ReviewDAO>,
        @ManyToOne(fetch = FetchType.EAGER)
        var student: UserDAO.StudentDAO,
        @OneToMany(mappedBy = "application", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var answers: MutableSet<AnswerDAO>
) {
    constructor() : this(0, Date(), 0, GrantCallDAO(), mutableSetOf<ReviewDAO>(), UserDAO.StudentDAO(), mutableSetOf<AnswerDAO>())
    constructor(app: ApplicationDTO, gc: GrantCallDAO, stud: UserDAO.StudentDAO) : this(app.id, app.submissionDate, app.status, gc, mutableSetOf<ReviewDAO>(), stud, mutableSetOf<AnswerDAO>())
    constructor(app: ApplicationDTO) : this(app.id, app.submissionDate, app.status, GrantCallDAO(), mutableSetOf<ReviewDAO>(), UserDAO.StudentDAO(), mutableSetOf<AnswerDAO>())

    override fun toString(): String {
        val grantCallId = grantCall.id
        val studentId = student.id
        return "ApplicationDAO=(id: $id, submissionDate: $submissionDate, status: $status, grantCallId: $grantCallId, " +
                "reviews: $reviews, student: $studentId, answers: $answers)"
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is ApplicationDAO) return false

        val a = this.id == other.id
        val b = true //this.submissionDate == other.submissionDate
        val c = this.status == other.status
        val d = this.grantCall.id == other.grantCall.id &&
                this.grantCall.title == other.grantCall.title &&
                this.grantCall.description == other.grantCall.description &&
                this.grantCall.funding == other.grantCall.funding
                //this.grantCall.openDate == other.grantCall.openDate &&
                //this.grantCall.closeDate == other.grantCall.closeDate
        val e = this.student.id == other.student.id &&
                this.student.name == other.student.name &&
                this.student.email == other.student.email &&
                this.student.address == other.student.address
        val f = this.answers == other.answers

        return a && b && c && d && e && f
    }
}







@Entity
open class UserDAO(
        @Id
        @GeneratedValue
        var id: Long,
        val username: String = "",
        var password: String = "",
        var role: String = "") {
    constructor(user: UserPasswordDTO) : this(0, user.username, user.password, user.role)
    constructor() : this(0, "user", "password", "role")

    @Entity
    data class StudentDAO(
            var name: String,
            var email: String,
            var address: String,
            @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
            var applications: MutableSet<ApplicationDAO>,
            @ManyToOne(fetch = FetchType.EAGER)
            var institution: InstitutionDAO,
            @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
            var cv: CVDAO?
    ) : UserDAO() {
        constructor() : this("name", "e-mail", "address", mutableSetOf<ApplicationDAO>(), InstitutionDAO(), null)
        constructor(stud: UserDTO) : this(stud.name, stud.email, stud.address, mutableSetOf<ApplicationDAO>(), InstitutionDAO(), null)
        constructor(stud: UserDTO, inst: InstitutionDAO) : this(stud.name, stud.email, stud.address, mutableSetOf<ApplicationDAO>(), inst, null)

        override fun toString(): String {
            var apps = "["
            var first = true
            for (app in applications) {
                if (!first)
                    apps += ", "
                apps += app.id

                first = false
            }
            apps += "]"

            val institutionId = institution.id

            return "UserDAO.StudentDAO=(id: $id, name: $name, email: $email, address: $address, applications: $apps, institution: $institutionId, cv: $cv)"
        }

        override fun hashCode(): Int {
            return id.hashCode()
        }

        override fun equals(other: Any?): Boolean {
            if (other == null || other !is StudentDAO) return false

            val a = this.id == other.id
            val b = this.name == other.name
            val c = this.address == other.address
            val d = this.applications == other.applications
            val e = this.institution.id == other.institution.id &&
                    this.institution.name == other.institution.name &&
                    this.institution.contact == other.institution.contact//compare institutions by id so it doesn't loop
            val f = this.cv == other.cv
            val g = this.email == other.email

            return a && b && c && d && e && f && g
        }
    }

    @Entity
    data class ReviewerDAO(
            var name: String,
            var email: String,
            var address: String,
            @OneToMany(mappedBy = "chair", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
            var panelsInCharge: MutableSet<PanelDAO>,
            @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
            var panels: MutableSet<PanelDAO>,
            @ManyToOne(fetch = FetchType.EAGER)
            var institution: InstitutionDAO,
            @OneToMany(mappedBy = "reviewer", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
            var reviews: MutableSet<ReviewDAO>
    ) :UserDAO(){
        constructor() : this("name", "e-mail", "address", mutableSetOf<PanelDAO>(), mutableSetOf<PanelDAO>(), InstitutionDAO(), mutableSetOf<ReviewDAO>())
        constructor(rev: UserDTO) : this(rev.name, rev.email, rev.address, mutableSetOf<PanelDAO>(), mutableSetOf<PanelDAO>(), InstitutionDAO(), mutableSetOf<ReviewDAO>())
        constructor(rev: UserDTO, inst: InstitutionDAO) : this(rev.name, rev.email, rev.address, mutableSetOf<PanelDAO>(), mutableSetOf<PanelDAO>(), inst, mutableSetOf<ReviewDAO>())

        override fun toString(): String {
            val institutionId = institution.id

            return "UserDAO.ReviewerDAO=(id: $id, name: $name, email: $email, address: $address, panelsInCharge: $panelsInCharge, panels: $panels, institutionId: $institutionId, " +
                    "reviews: $reviews)"
        }

        override fun hashCode(): Int {
            return id.hashCode()
        }

        override fun equals(other: Any?): Boolean {
            if (other == null || other !is ReviewerDAO) return false

            val a = this.id == other.id
            val b = this.name == other.name
            val c = this.address == other.address
            val d = this.panelsInCharge == other.panelsInCharge
            val e = this.institution.id == other.institution.id &&
                    this.institution.name == other.institution.name &&
                    this.institution.contact == other.institution.contact//compare institutions by id so it doesn't loop
            val f = this.reviews == other.reviews
            val g = this.panels == other.panels

            return a && b && c && d && e && f && g
        }
    }

    @Entity
    data class SponsorDAO(
            var name: String,
            var contact: String,
            @OneToMany(mappedBy = "sponsor", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
            var grantCalls: MutableSet<GrantCallDAO>
    ):UserDAO() {
        constructor() : this("name", "contact", mutableSetOf<GrantCallDAO>())
        constructor(sponsor: OrganizationDTO) : this(sponsor.name, sponsor.contact, mutableSetOf<GrantCallDAO>())
    }
}


@Entity
data class GrantCallDAO (
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
        @ManyToMany(fetch = FetchType.EAGER)
        var dataItems: MutableSet<DataItemDAO>,
        @ManyToOne(fetch = FetchType.EAGER)
        var sponsor: UserDAO.SponsorDAO
) {
    constructor() : this(0, "title", "description", 0.00, Date(), Date(), mutableSetOf<ApplicationDAO>(), null, mutableSetOf<DataItemDAO>(), UserDAO.SponsorDAO())
    constructor(gc: GrantCallDTO) : this(0, gc.title, gc.description, gc.funding, gc.openDate, gc.closeDate, mutableSetOf<ApplicationDAO>(), null, mutableSetOf<DataItemDAO>(), UserDAO.SponsorDAO())
    constructor(gc: GrantCallDTO, sponsor: UserDAO.SponsorDAO) : this(0, gc.title, gc.description, gc.funding, gc.openDate, gc.closeDate, mutableSetOf<ApplicationDAO>(), null, mutableSetOf<DataItemDAO>(), sponsor)


    override fun toString(): String {
        val sponsorId = sponsor.id

        return "GrantCallDAO=(id: $id, title: $title, description: $description, funding: $funding, openDate: $openDate, closeDate: $closeDate, " +
                "applications: $applications, panel: $panel, dataItems: $dataItems, sponsorId: $sponsorId)"
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is GrantCallDAO) return false

        val a = this.id == other.id
        val b = this.title == other.title
        val c = this.description == other.description
        val d = this.funding == other.funding
        val e = this.applications == other.applications
        val f = this.panel == other.panel
        val g = this.dataItems == other.dataItems
        val h = true//this.openDate == other.openDate
        val i = true//this.closeDate == other.closeDate
        val j = this.sponsor.id == other.sponsor.id &&
                this.sponsor.name == other.sponsor.name &&
                this.sponsor.contact == other.sponsor.contact

        return a && b && c && d && e && f && g && h && i && j
    }
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
    constructor(answer: AnswerDTO, dItem: DataItemDAO, app: ApplicationDAO) : this(answer.id, answer.name, answer.value, answer.datatype, dItem, app)

    override fun toString(): String {
        val dataItemId = dataItem.id
        val applicationId = application.id

        return "AnswerDAO=(id: $id, name: $name, value: $value, dataType: $dataType, dataItemId: $dataItemId, applicationId: $applicationId)"
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is AnswerDAO) return false

        val a = this.id == other.id
        val b = this.name == other.name
        val c = this.value == other.value
        val d = this.dataItem.id == other.dataItem.id &&
                this.dataItem.name == other.dataItem.name &&
                this.dataItem.dataType == other.dataItem.dataType &&
                this.dataItem.isMandatory == other.dataItem.isMandatory
        val e = this.application.id == other.application.id &&
                //this.application.submissionDate == other.application.submissionDate &&
                this.application.status == other.application.status
        val f = this.dataType == other.dataType

        return a && b && c && d && e && f
    }
}

@Entity
data class CVItemDAO(
        @Id
        @GeneratedValue
        var id: Long,
        var name: String,
        var value: String,
        var dataType: String,
        @ManyToOne
        var CVRequirement: CVRequirementDAO,
        @ManyToOne
        var CV: CVDAO
) {
    constructor() : this(0, "name", "value", "data type", CVRequirementDAO(), CVDAO())
    constructor(cvItem: CVItemDTO) : this(cvItem.id, cvItem.name, cvItem.value, cvItem.datatype, CVRequirementDAO(), CVDAO())
    constructor(cvItem: CVItemDTO, cvReq: CVRequirementDAO, cv: CVDAO) : this(cvItem.id, cvItem.name, cvItem.value, cvItem.datatype, cvReq, cv)

    override fun toString(): String {
        val cvRequirementId = CVRequirement.id
        val cvId = CV.id

        return "CVItemDAO=(id: $id, name: $name, value: $value, dataType: $dataType, cvRequirementId: $cvRequirementId, cvId: $cvId)"
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is CVItemDAO) return false

        val a = this.id == other.id
        val b = this.name == other.name
        val c = this.value == other.value
        val d = this.CVRequirement.id == other.CVRequirement.id &&
                this.CVRequirement.name == other.CVRequirement.name &&
                this.CVRequirement.dataType == other.CVRequirement.dataType &&
                this.CVRequirement.isMandatory == other.CVRequirement.isMandatory
        val e = this.CV.id == other.CV.id
        val f = this.dataType == other.dataType

        return a && b && c && d && e && f
    }

}

@Entity
data class CVDAO(
        @Id
        @GeneratedValue
        var id: Long,
        @OneToMany(mappedBy = "CV", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        val CVItems: MutableSet<CVItemDAO>,
        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var student: UserDAO.StudentDAO
) {
    constructor() : this(0, mutableSetOf<CVItemDAO>(), UserDAO.StudentDAO())
    constructor(stud: UserDAO.StudentDAO) : this(0, mutableSetOf(), stud)

    override fun toString(): String {
        val studentId = student.id

        return "CVItemDAO=(id: $id, CVItems: $CVItems, studentId: $studentId)"
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is CVDAO) return false

        val a = this.id == other.id
        val b = this.CVItems == other.CVItems
        val c = this.student.id == other.student.id

        return a && b && c
    }
}

@Entity
data class CVRequirementDAO (
        @Id
        @GeneratedValue
        var id: Long,
        var name: String,
        var dataType: String,
        var isMandatory: Boolean,
        @OneToMany(mappedBy = "CVRequirement", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var CVItems: MutableSet<CVItemDAO>
) {
    constructor() : this(0, "name", "data type", false,  mutableSetOf<CVItemDAO>())
    constructor(cvr: CVRequirementDTO) : this(0, cvr.name, cvr.datatype, cvr.isMandatory, mutableSetOf<CVItemDAO>())
    //no overrides needed
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
        var grantCalls: MutableSet<GrantCallDAO>,
        @OneToMany(mappedBy = "dataItem", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var answers: MutableSet<AnswerDAO>
) {
    constructor() : this(0, "name", "data type", false, mutableSetOf<GrantCallDAO>(), mutableSetOf<AnswerDAO>())
    constructor(dItem: DataItemDTO) : this(0, dItem.name, dItem.datatype, dItem.isMandatory, mutableSetOf<GrantCallDAO>(), mutableSetOf<AnswerDAO>())

    override fun toString(): String {
        var calls = "["
        var first = true
        for(call in grantCalls) {
            if(!first)
                calls += ", "
            calls += call.id

            first = false
        }
        calls += "]"

        return "DataItemDAO=(id: $id, name: $name, dataType: $dataType, isMandatory: $isMandatory, calls: $calls, answers: $answers)"
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is DataItemDAO) return false

        val a = this.id == other.id
        val b = this.name == other.name
        val c = this.dataType == other.dataType
        val d = this.grantCalls.map { it.id } == other.grantCalls.map { it.id }
        val e = this.answers == other.answers

        return a && b && c && d && e
    }
}

@Entity
data class InstitutionDAO(
        @Id
        @GeneratedValue
        var id: Long,
        var name: String,
        var contact: String,
        @OneToMany(mappedBy = "institution", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var students: MutableSet<UserDAO.StudentDAO>,
        @OneToMany(mappedBy = "institution", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var reviewers: MutableSet<UserDAO.ReviewerDAO>
) {
    constructor() : this(0, "name", "contact", mutableSetOf<UserDAO.StudentDAO>(), mutableSetOf<UserDAO.ReviewerDAO>())
    constructor(inst: OrganizationDTO) : this(inst.id, inst.name, inst.contact, mutableSetOf<UserDAO.StudentDAO>(), mutableSetOf<UserDAO.ReviewerDAO>())
    //no overrides needed
}

@Entity
data class PanelDAO(
        @Id
        @GeneratedValue
        var id: Long,
        @ManyToOne
        var chair: UserDAO.ReviewerDAO?,
        @ManyToMany(mappedBy = "panels", fetch = FetchType.EAGER)
        var reviewers: MutableSet<UserDAO.ReviewerDAO>,
        @OneToOne(fetch = FetchType.EAGER)
        var grantCall: GrantCallDAO
) {
    constructor() : this(0, null, mutableSetOf<UserDAO.ReviewerDAO>(), GrantCallDAO())
    constructor(panel: PanelDTO) : this(panel.id, UserDAO.ReviewerDAO(), mutableSetOf<UserDAO.ReviewerDAO>(), GrantCallDAO())
    constructor(gc: GrantCallDAO) : this(0,null, mutableSetOf(), gc)


    override fun toString(): String {
        var reviewers = "["
        var first = true
        for(reviewer in this.reviewers) {
            if(!first)
                reviewers += ", "
            reviewers += reviewer.id

            first = false
        }
        reviewers += "]"


        return "PanelDAO=(id: $id, reviewers: $reviewers)"
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is PanelDAO) return false

        val a = this.id == other.id
        val b = this.reviewers.map { it.id } == other.reviewers.map { it.id }

        return a && b
    }
}

@Entity
data class ReviewDAO(
        @Id
        @GeneratedValue
        var id: Long,
        var isAccepted: Boolean,
        var comment: String,
        @ManyToOne
        var application: ApplicationDAO,
        @ManyToOne
        var reviewer: UserDAO.ReviewerDAO
) {
    constructor() : this(0, false, "comment", ApplicationDAO(), UserDAO.ReviewerDAO())
    constructor(review: ReviewDTO) : this(review.id, review.isAccepted, review.comment, ApplicationDAO(), UserDAO.ReviewerDAO())
    constructor(review: ReviewDTO, app: ApplicationDAO, reviewer: UserDAO.ReviewerDAO) : this(review.id, review.isAccepted, review.comment, app, reviewer)

    override fun toString(): String {
        val applicationId = application.id
        val reviewerId = reviewer.id

        return "ReviewDAO=(id: $id, isAccepted: $isAccepted, comment: $comment, applicationId: $applicationId, reviewerId: $reviewerId)"
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is ReviewDAO) return false

        val a = this.id == other.id
        val b = this.isAccepted == other.isAccepted
        val c = this.comment == other.comment
        val d = this.application.id == other.application.id &&
                //this.application.submissionDate == other.application.submissionDate &&
                this.application.status == other.application.status
        val e = this.reviewer.id == other.reviewer.id &&
                this.reviewer.name == other.reviewer.name &&
                this.reviewer.email == other.reviewer.address

        return a && b && c && d && e
    }
}

