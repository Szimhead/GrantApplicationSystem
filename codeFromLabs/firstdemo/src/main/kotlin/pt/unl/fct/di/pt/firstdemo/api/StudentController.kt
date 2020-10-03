package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.StudentService

@RestController
class StudentController(val students: StudentService): StudentAPI {

    override fun getAll() = students.getAll()

    override fun getOne(studentNr:Long) = students.getOne(studentNr)

    override fun getApplications(studentNr: Long) = students.getApplications(studentNr)

    override fun getOneApplication(studentNr: Long, id:Long) = students.getOneApplication(studentNr, id)

    override fun delete(studentNr: Long) = students.delete(studentNr)

    override fun update(studentNr:Long) = students.update(studentNr)
}