package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.InstitutionDTO
import pt.unl.fct.di.pt.firstdemo.api.ReviewerDTO
import pt.unl.fct.di.pt.firstdemo.api.StudentDTO

@Service
class InstitutionService {

    fun getAll() = listOf<InstitutionDTO>(InstitutionDTO(1, "FCT UNL"))

    fun getOne(id:Long) = InstitutionDTO(2, "IMS UNL")

    fun addInstitution() {
        TODO("Not yet implemented")
    }

    fun deleteInstitution(id:Long) {
        TODO("Not yet implemented")
    }

    fun updateInstitution(id:Long) {
        TODO("Not yet implemented")
    }

    /* student handling */
    fun getStudents(id:Long) = listOf<StudentDTO>(StudentDTO(1, "Luke Smith"))

    fun getOneStudent(id:Long, studentNr:Long) = StudentDTO(1, "Luke Smith")

    fun addStudent(id:Long) {
        TODO("Not yet implemented")
    }


    /* reviewer handling */
    fun getReviewers(id:Long) = listOf<ReviewerDTO>(ReviewerDTO(1, "John Smith"))

    fun getOneReviewer(id:Long, reviewerNr:Long) = ReviewerDTO(1, "John Smith")

    fun addReviewer(id:Long) {
        TODO("Not yet implemented")
    }
}