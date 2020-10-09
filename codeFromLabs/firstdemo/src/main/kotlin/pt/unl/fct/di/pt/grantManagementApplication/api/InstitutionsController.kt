package pt.unl.fct.di.pt.grantManagementApplication.api

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.grantManagementApplication.services.InstitutionsService

@Api(value="Institutions", description = "Operation management of Institutions", tags=["Institutions"])
@RestController
class InstitutionsController(val institutions: InstitutionsService): InstitutionsAPI {

    override fun getAll() = institutions.getAll()

    override fun getOne(id:Long) = institutions.getOne(id)

    override fun addInstitution(id: Long) = institutions.addInstitution(id)

    override fun deleteInstitution(id:Long) = institutions.deleteInstitution(id)

    override fun editInstitution(id:Long) = institutions.editInstitution(id)

    /* student handling */
    override fun getStudents(id:Long) = institutions.getStudents(id)

    override fun getOneStudent(id:Long, studentNr:Long) = institutions.getOneStudent(id, studentNr)

    override fun addStudent(id: Long, studentNr: Long) = institutions.addStudent(id, studentNr)

    /* reviewer handling */
    override fun getReviewers(id:Long) = institutions.getReviewers(id)

    override fun getOneReviewer(id:Long, reviewerNr:Long) = institutions.getOneReviewer(id, reviewerNr)

    override fun addReviewer(id: Long, reviewerNr: Long) = institutions.addReviewer(id, reviewerNr)

}