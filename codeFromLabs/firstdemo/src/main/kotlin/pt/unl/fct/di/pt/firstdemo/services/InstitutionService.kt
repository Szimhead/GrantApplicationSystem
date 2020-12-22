package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pt.unl.fct.di.pt.firstdemo.api.OrganizationDTO
import pt.unl.fct.di.pt.firstdemo.api.UserDTO
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.InstitutionRepository
import pt.unl.fct.di.pt.firstdemo.model.ReviewerRepository
import pt.unl.fct.di.pt.firstdemo.model.StudentRepository
import pt.unl.fct.di.pt.firstdemo.model.UserRepository

@Service
class InstitutionService(val inst: InstitutionRepository, val studs: StudentRepository, val revs: ReviewerRepository, val users: UserRepository) {

    fun getAll() : Iterable<InstitutionDAO> = inst.findAll()

    fun getOne(id:Long) : InstitutionDAO = inst.findById(id).orElseThrow {
        NotFoundException("Institution with id $id not found")
    }

    @Transactional
    fun addInstitution(institution: InstitutionDAO) {
        inst.save(institution)
    }

    fun deleteInstitution(institution: InstitutionDAO) {
        inst.delete(institution)
    }

    fun editInstitution(editedInst: InstitutionDAO, newInst: InstitutionDAO) {
        editedInst.name = newInst.name
        editedInst.contact = newInst.contact
        inst.save(editedInst)
    }

    /* student handling */
    fun getStudentsFromInstitution(institution: InstitutionDAO): MutableSet<UserDAO.StudentDAO> {
        return institution.students
    }

    @Transactional
    fun addStudentToInstitution(institution: InstitutionDAO, student: UserDAO.StudentDAO) {
        institution.students.add(student)
        student.institution=institution
        studs.save(student)
    }

    fun getStudentUser(student: UserDAO.StudentDAO) : UserDAO{
        return UserDAO(0, student.email,"password","STUDENT")
    }

    /* reviewer handling */
    fun getReviewersFromInstitution(institution: InstitutionDAO): MutableSet<UserDAO.ReviewerDAO> {
        return institution.reviewers
    }

    fun addReviewer(institution: InstitutionDAO, reviewer: UserDAO.ReviewerDAO) {
        //reviewer.institution = institution
        revs.save(reviewer)
    }

    fun getReviewerUser(reviewer: UserDAO.ReviewerDAO) : UserDAO{
        return UserDAO(0, reviewer.email,"password","REVIEWER")
    }
}