package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.InstitutionDAO
import pt.unl.fct.di.pt.firstdemo.services.InstitutionService
import pt.unl.fct.di.pt.firstdemo.services.ReviewerDAO
import pt.unl.fct.di.pt.firstdemo.services.StudentDAO

@RestController
class InstitutionController(val institutions: InstitutionService): InstitutionAPI {

    override fun getAll() = institutions.getAll().map { OrganizationDTO(it) }

    override fun getOne(id:Long) = OrganizationDTO(institutions.getOne(id))

    override fun addInstitution(institution: OrganizationDTO) = institutions.addInstitution(InstitutionDAO(institution))

    override fun deleteInstitution(id:Long) = institutions.deleteInstitution(id)

    override fun editInstitution(id:Long, institution: OrganizationDTO) = institutions.editInstitution(id, InstitutionDAO(institution))

    /* student handling */
    override fun getStudents(id:Long) = institutions.getStudents(id).map { UserDTO(it) }

    override fun addStudent(id: Long, student: UserDTO) = institutions.addStudent(id, StudentDAO(student))

    /* reviewer handling */
    override fun getReviewers(id:Long) = institutions.getReviewers(id).map { UserDTO(it) }

    override fun addReviewer(id: Long, reviewer: UserDTO) = institutions.addReviewer(id, ReviewerDAO(reviewer))

}