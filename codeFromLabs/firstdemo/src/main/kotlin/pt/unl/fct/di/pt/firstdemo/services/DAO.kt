package pt.unl.fct.di.pt.firstdemo.services

import pt.unl.fct.di.pt.firstdemo.api.ApplicationDTO
import pt.unl.fct.di.pt.firstdemo.api.GrantCallDTO
import pt.unl.fct.di.pt.firstdemo.api.PanelDTO
import pt.unl.fct.di.pt.firstdemo.api.UserDTO
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
        @ManyToMany
        var panels: List<PanelDAO>
) {
    constructor() : this(0, "name", "e-mail", "address", listOf<PanelDAO>())
    constructor(rev: UserDTO) : this(rev.id, rev.name, rev.email, rev.address, listOf<PanelDAO>())
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
        var panel: PanelDAO?,
        @ManyToMany
        var dataItems: List<DataItemDAO>
) {
    constructor() : this(0, "title", "description", 0.00, Date(), Date(), listOf<ApplicationDAO>(), null, listOf<DataItemDAO>())
    constructor(gc: GrantCallDTO) : this(0, gc.title, gc.description, gc.funding, gc.openDate, gc.closeDate, listOf<ApplicationDAO>(), null, listOf<DataItemDAO>())
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
}

@Entity
data class DataItemDAO(
        @Id
        @GeneratedValue
        var id: Long,
        val name: String,
        val dataType: String,
        val isMandatory: Boolean
) {
    constructor() : this(0, "name", "data type", false)
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
        var id: Long
) {
    constructor() : this(0)
    constructor(panel: PanelDTO) : this(panel.id)
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