package pt.unl.fct.di.pt.firstdemo.services

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

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