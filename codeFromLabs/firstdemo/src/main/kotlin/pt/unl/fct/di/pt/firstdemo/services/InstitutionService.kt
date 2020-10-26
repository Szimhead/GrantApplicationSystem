package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pt.unl.fct.di.pt.firstdemo.api.OrganizationDTO
import pt.unl.fct.di.pt.firstdemo.api.UserDTO
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.InstitutionRepository
import pt.unl.fct.di.pt.firstdemo.model.ReviewerRepository
import pt.unl.fct.di.pt.firstdemo.model.StudentRepository

@Service
class InstitutionService(val inst: InstitutionRepository, val studs: StudentRepository, val revs: ReviewerRepository) {

    fun getAll() = inst.findAll()

    fun getOne(id:Long) = inst.findById(id).orElse(null)

    @Transactional
    fun addInstitution(institution: InstitutionDAO) {
        inst.save(institution)
    }

    fun deleteInstitution(id:Long) {
        val deletedInst = inst.findById(id).orElse(null)
        inst.delete(deletedInst)
    }

    fun editInstitution(id:Long, institution: InstitutionDAO) {
        val editedInst = inst.findById(id).orElse(null)
        editedInst.name = institution.name
        editedInst.contact = institution.contact
        inst.save(editedInst)
    }

    /* student handling */
    fun getStudents(id:Long): MutableSet<StudentDAO> {
        val institution = inst.findById(id).orElse(null)
        return institution.students
    }

    @Transactional
    fun addStudent(id:Long, student: StudentDAO) {
        val institution = inst.findById(id).orElseThrow {
            NotFoundException("Institution with id $id not found")
        }
        student.institution = institution;
        institution.students.add(student)
        studs.save(student)
    }


    /* reviewer handling */
    fun getReviewers(id:Long): MutableSet<ReviewerDAO> {
        val institution = inst.findById(id).orElse(null)
        return institution.reviewers
    }

    fun addReviewer(id:Long, reviewer: ReviewerDAO) {
        val institution = inst.findById(id).orElse(null)
        reviewer.institution = institution
        revs.save(reviewer)
    }
}