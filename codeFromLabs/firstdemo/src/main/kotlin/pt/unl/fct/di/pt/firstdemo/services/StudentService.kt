package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.ApplicationDTO
import pt.unl.fct.di.pt.firstdemo.api.StudentDTO
import java.util.*

@Service
class StudentService {
    fun getAll() = listOf<StudentDTO>(StudentDTO(1, "Luke Smith"))

    fun getOne(id:Long) = StudentDTO(2, "Anne Smith")

    fun getApplications(studentNr: Long) = listOf<ApplicationDTO>(ApplicationDTO(1, Date(), 0))

    fun getOneApplication(studentNr: Long, id:Long) = ApplicationDTO(1, Date(), 0)

    fun delete(studentNr:Long) {
        TODO("Not yet implemented")
    }

    fun update(studentNr: Long) {
        TODO("Not yet implemented")
    }
}