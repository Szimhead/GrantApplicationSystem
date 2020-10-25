package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.StudentDAO
import pt.unl.fct.di.pt.firstdemo.services.StudentService

@RestController
class StudentController(val students: StudentService): StudentAPI {

    override fun getAll() = students.getAll().map { UserDTO(it) }

    override fun getOne(studentNr:Long) = UserDTO(students.getOne(studentNr))

    override fun addStudent(student: UserDTO) = students.addStudent(StudentDAO(student))

    override fun deleteStudent(studentNr: Long) = students.deleteStudent(studentNr)

    override fun editStudent(studentNr:Long, student: UserDTO) = students.editStudent(studentNr, StudentDAO(student))

    override fun getApplications(studentNr: Long) = students.getApplications(studentNr)

    override fun getOneApplication(studentNr: Long, id:Long) = students.getOneApplication(studentNr, id)

    override fun getCV(studentNr:Long) = students.getCV(studentNr)

    override fun addCVItem(studentNr: Long, name: String) = students.addCVItem(studentNr, name)

    override fun editCVItem(studentNr: Long, name: String) = students.editCVItem(studentNr, name)

    override fun deleteCVItem(studentNr: Long, name: String) = students.deleteCVItem(studentNr, name)

}