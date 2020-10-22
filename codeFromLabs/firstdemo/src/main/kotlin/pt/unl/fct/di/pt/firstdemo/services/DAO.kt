package pt.unl.fct.di.pt.firstdemo.services

import pt.unl.fct.di.pt.firstdemo.api.*
import java.util.*
import javax.persistence.*

@Entity
data class ApplicationDAO(
    @Id
    @GeneratedValue
    var id: Long,
    val submissionDate: Date,
    val status: Int,
    @ManyToOne
    var grantCall: GrantCallDAO
) {
    constructor() : this(0, Date(), 0, GrantCallDAO())
    constructor(app: ApplicationDTO) : this(app.id, app.submissionDate, app.status, GrantCallDAO())
}

@Entity
data class StudentDAO(
        @Id
        @GeneratedValue
        var id: Long,
        val name: String,
        val email: String,
        val address: String
) {
    constructor() : this(0, "name", "e-mail", "address")
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
        var panels: MutableList<PanelDAO>
) {
    constructor() : this(0, "name", "e-mail", "address", emptyList<PanelDAO>() as MutableList<PanelDAO>)
    constructor(rev: UserDTO) : this(rev.id, rev.name, rev.email, rev.address, emptyList<PanelDAO>() as MutableList<PanelDAO>)
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
        val name: String,
        val dataType: String,
        val isMandatory: Boolean
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
        val name: String,
        val contact: String
) {
    constructor() : this(0, "name", "contact")
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
        val comment: String
) {
    constructor() : this(0, false, "comment")
}