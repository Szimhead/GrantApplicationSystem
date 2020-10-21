package pt.unl.fct.di.pt.firstdemo.services

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class ApplicationDAO(
    @Id
    @GeneratedValue
    var id: Long,
    val submissionDate: Date,
    val status: Int
) {
    constructor() : this(0, Date(), 0)
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
        val address: String
) {
    constructor() : this(0, "name", "e-mail", "address")
}

@Entity
data class GrantCallDAO(
        @Id
        @GeneratedValue
        var id: Long,
        val title: String,
        val description: String,
        val funding: Double,
        val openDate: Date,
        val closeDate: Date
) {
    constructor() : this(0, "title", "description", 0.00, Date(), Date())
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