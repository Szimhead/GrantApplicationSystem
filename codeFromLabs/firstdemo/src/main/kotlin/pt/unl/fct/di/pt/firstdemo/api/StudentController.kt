package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.CVItemDAO
import pt.unl.fct.di.pt.firstdemo.services.GrantCallService
import pt.unl.fct.di.pt.firstdemo.services.StudentDAO
import pt.unl.fct.di.pt.firstdemo.services.StudentService

@RestController
class StudentController(val students: StudentService, val gc: GrantCallService): StudentAPI {

    override fun getAll() = students.getAll().map { UserDTO(it) }

    override fun getOne(studentNr:Long) = UserDTO(students.getOne(studentNr))

    override fun deleteStudent(studentNr: Long) = students.deleteStudent(studentNr)

    override fun editStudent(studentNr:Long, student: UserDTO) = students.editStudent(students.getOne(studentNr), StudentDAO(student, students.getOne(studentNr).institution))

    override fun getApplications(studentNr: Long) = students.getApplications(studentNr).map { ApplicationDTO(it) }.toMutableSet()

    override fun getCV(studentNr:Long) = CVDTO(students.getCV(students.getOne(studentNr)))

    override fun getCVItem(studentNr:Long, id: Long) = CVItemDTO(students.getCVItem(students.getCV(students.getOne(studentNr)), id))

    override fun addCVItem(studentNr: Long, id: Long) = students.addCVItem(students.getOne(studentNr), students.getCVItem(students.getCV(students.getOne(studentNr)), id))

    override fun editCVItem(studentNr: Long, id: Long, cvItem: CVItemDTO) = students.editCVItem(students.getOne(studentNr), students.getCVItem(students.getCV(students.getOne(studentNr)), id), CVItemDAO(cvItem, c))

    override fun deleteCVItem(studentNr: Long, id: Long) = students.deleteCVItem(students.getOne(studentNr), students.getCVItem(students.getCV(students.getOne(studentNr)), id))

}