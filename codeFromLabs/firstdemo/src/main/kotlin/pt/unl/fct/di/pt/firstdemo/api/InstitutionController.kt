package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.*

@RestController
class InstitutionController(val inst: InstitutionService, val users: UserService): InstitutionAPI {

    override fun getAll() = inst.getAll().map { OrganizationDTO(it) }

    override fun getOne(id:Long) = OrganizationDTO(inst.getOne(id))

    override fun addInstitution(institution: OrganizationDTO) = inst.addInstitution(InstitutionDAO(institution))

    override fun deleteInstitution(id:Long) = inst.deleteInstitution(inst.getOne(id))

    override fun editInstitution(id:Long, institution: OrganizationDTO) = inst.editInstitution(inst.getOne(id), InstitutionDAO(institution))

    /* student handling */
    override fun getStudents(id:Long) = inst.getStudentsFromInstitution(inst.getOne(id)).map { UserDTO(it) }

    override fun addStudent(id: Long, student: UserDTO) {
        val institution = inst.getOne(id)
        inst.addStudentToInstitution(institution, StudentDAO(student, institution))
        users.addUser(inst.getStudentUser(StudentDAO(student, institution)))
    }

    /* reviewer handling */
    override fun getReviewers(id:Long) = inst.getReviewersFromInstitution(inst.getOne(id)).map { UserDTO(it) }

    override fun addReviewer(id: Long, reviewer: UserDTO) {
        val institution = inst.getOne(id)
        inst.addReviewer(institution, ReviewerDAO(reviewer, institution))
        users.addUser(inst.getReviewerUser(ReviewerDAO(reviewer, institution)))
    }

}