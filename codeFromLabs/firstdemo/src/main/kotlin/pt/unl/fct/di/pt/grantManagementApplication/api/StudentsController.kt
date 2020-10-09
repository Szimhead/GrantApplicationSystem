package pt.unl.fct.di.pt.grantManagementApplication.api

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.grantManagementApplication.services.StudentsService

@Api(value="Students", description = "Operation management of Students", tags=["Students"])
@RestController
class StudentsController(val students: StudentsService): StudentsAPI {

    override fun getAll() = students.getAll()

    override fun getOne(studentNr:Long) = students.getOne(studentNr)

    override fun addStudent(studentNr: Long) = students.addStudent(studentNr)

    override fun deleteStudent(studentNr: Long) = students.deleteStudent(studentNr)

    override fun editStudent(studentNr:Long) = students.editStudent(studentNr)

    override fun getApplications(studentNr: Long) = students.getApplications(studentNr)

    override fun getOneApplication(studentNr: Long, id:Long) = students.getOneApplication(studentNr, id)

    override fun getCV(studentNr:Long) = students.getCV(studentNr)

    override fun addCVItem(studentNr: Long, name: String) = students.addCVItem(studentNr, name)

    override fun editCVItem(studentNr: Long, name: String) = students.editCVItem(studentNr, name)

    override fun deleteCVItem(studentNr: Long, name: String) = students.deleteCVItem(studentNr, name)

}