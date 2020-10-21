package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.InstitutionService

@RestController
class InstitutionController(val institutions: InstitutionService): InstitutionAPI {

    override fun getAll() = institutions.getAll()

    override fun getOne(id:Long) = institutions.getOne(id)

    override fun addInstitution(institution: OrganizationDTO) = institutions.addInstitution(institution)

    override fun deleteInstitution(id:Long) = institutions.deleteInstitution(id)

    override fun editInstitution(id:Long) = institutions.editInstitution(id)

    /* student handling */
    override fun getStudents(id:Long) = institutions.getStudents(id)

    override fun getOneStudent(id:Long, studentNr:Long) = institutions.getOneStudent(id, studentNr)

    override fun addStudent(id: Long, student: UserDTO) = institutions.addStudent(id, student)

    /* reviewer handling */
    override fun getReviewers(id:Long) = institutions.getReviewers(id)

    override fun getOneReviewer(id:Long, reviewerNr:Long) = institutions.getOneReviewer(id, reviewerNr)

    override fun addReviewer(id: Long, reviewer: UserDTO) = institutions.addReviewer(id, reviewer)

}