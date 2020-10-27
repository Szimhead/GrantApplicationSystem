package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.ApplicationDTO
import pt.unl.fct.di.pt.firstdemo.api.CVDTO
import pt.unl.fct.di.pt.firstdemo.api.CVItemDTO
import pt.unl.fct.di.pt.firstdemo.api.UserDTO
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.ApplicationRepository
import pt.unl.fct.di.pt.firstdemo.model.CVItemRepository
import pt.unl.fct.di.pt.firstdemo.model.StudentRepository
import java.util.*

@Service
class StudentService (val students: StudentRepository, val applications: ApplicationRepository, cvItems: CVItemRepository){
    fun getAll() : Iterable<StudentDAO> = students.findAll()

    fun getOne(id:Long): StudentDAO = students.findById(id).orElseThrow{
        NotFoundException("Student with $id not found")
    }

    fun addStudent(student: StudentDAO){
        student.id =0
        students.save(student)
    }

    fun deleteStudent(studentNr:Long) {
        val student = students.findById(studentNr).orElseThrow{
            NotFoundException("Reviewer with $studentNr not found")
        }
        students.delete(student)
    }

    fun editStudent(studentNr: Long, student: StudentDAO){
        val editedStudent = students.findById(studentNr).orElseThrow{
            NotFoundException("Reviewer with $studentNr not found")
        };
        editedStudent.address=student.address
        editedStudent.email =student.email
        editedStudent.institution=student.institution
        editedStudent.name=student.name

        students.save(editedStudent)
    }

    //application handling
    fun getApplications(studentNr: Long) = listOf<ApplicationDTO>(ApplicationDTO(1, Date(), 0, 0))

    fun getOneApplication(studentNr: Long, id:Long) = ApplicationDTO(1, Date(), 0, 0)

    fun getCV(studentNr:Long) = CVDTO(listOf())

    fun addCVItem(studentNr: Long, name: String) {
        TODO("Not yet implemented")
    }

    //cvItem handling
    fun editCVItem(studentNr: Long, name: String) {
        TODO("Not yet implemented")
    }

    fun deleteCVItem(studentNr: Long, name: String) {
        TODO("Not yet implemented")
    }


}