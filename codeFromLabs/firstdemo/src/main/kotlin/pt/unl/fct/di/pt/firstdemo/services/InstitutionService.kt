package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.OrganizationDTO
import pt.unl.fct.di.pt.firstdemo.api.UserDTO

@Service
class InstitutionService {

    fun getAll() = listOf<OrganizationDTO>(OrganizationDTO(1, "FCT UNL", "no contact"))

    fun getOne(id:Long) = OrganizationDTO(2, "IMS UNL", "no contact")

    fun addInstitution(id: Long) {
        TODO("Not yet implemented")
    }

    fun deleteInstitution(id:Long) {
        TODO("Not yet implemented")
    }

    fun editInstitution(id:Long) {
        TODO("Not yet implemented")
    }

    /* student handling */
    fun getStudents(id:Long) = listOf<UserDTO>(UserDTO(1, "John Smith", "john.s@gmail.com", "no address"))

    fun getOneStudent(id:Long, studentNr:Long) = UserDTO(1, "John Smith", "john.s@gmail.com", "no address")

    fun addStudent(id:Long, studentNr: Long) {
        TODO("Not yet implemented")
    }


    /* reviewer handling */
    fun getReviewers(id:Long) = listOf<UserDTO>(UserDTO(1, "John Smith", "john.s@gmail.com", "no address"))

    fun getOneReviewer(id:Long, reviewerNr:Long) = UserDTO(1, "John Smith", "john.s@gmail.com", "no address")

    fun addReviewer(id:Long, reviewerNr: Long) {
        TODO("Not yet implemented")
    }
}