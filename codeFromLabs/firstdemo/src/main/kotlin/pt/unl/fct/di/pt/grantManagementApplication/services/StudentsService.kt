package pt.unl.fct.di.pt.grantManagementApplication.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.grantManagementApplication.api.ApplicationDTO
import pt.unl.fct.di.pt.grantManagementApplication.api.CVDTO
import pt.unl.fct.di.pt.grantManagementApplication.api.UserDTO
import java.util.*

@Service
class StudentsService {
    fun getAll() = listOf<UserDTO>(UserDTO(1, "John Smith", "john.s@gmail.com", "no address"))

    fun getOne(id:Long) = UserDTO(1, "John Smith", "john.s@gmail.com", "no address")

    fun addStudent(studentNr: Long) {
        TODO("Not yet implemented")
    }

    fun deleteStudent(studentNr:Long) {
        TODO("Not yet implemented")
    }

    fun editStudent(studentNr: Long) {
        TODO("Not yet implemented")
    }

    fun getApplications(studentNr: Long) = listOf<ApplicationDTO>(ApplicationDTO(1, Date(), 0))

    fun getOneApplication(studentNr: Long, id:Long) = ApplicationDTO(1, Date(), 0)

    fun getCV(studentNr:Long) = CVDTO(listOf())

    fun addCVItem(studentNr: Long, name: String) {
        TODO("Not yet implemented")
    }

    fun editCVItem(studentNr: Long, name: String) {
        TODO("Not yet implemented")
    }

    fun deleteCVItem(studentNr: Long, name: String) {
        TODO("Not yet implemented")
    }


}