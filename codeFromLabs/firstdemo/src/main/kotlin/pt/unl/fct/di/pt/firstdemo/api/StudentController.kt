package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.CVItemDAO
import pt.unl.fct.di.pt.firstdemo.services.GrantCallService
import pt.unl.fct.di.pt.firstdemo.services.StudentDAO
import pt.unl.fct.di.pt.firstdemo.services.StudentService

@RestController
class StudentController(val students: StudentService, val gc: GrantCallService): StudentAPI {

    override fun getAll() = students.getAll().map { UserDTO(it) }

    override fun getOne(id:Long) = UserDTO(students.getOne(id))

    override fun deleteStudent(id: Long) = students.deleteStudent(id)

    override fun editStudent(id:Long, student: UserDTO) = students.editStudent(students.getOne(id), StudentDAO(student, students.getOne(id).institution))

    override fun getApplications(id: Long) = students.getApplications(id).map { ApplicationDTO(it) }.toMutableSet()

    override fun getCV(id:Long) = CVDTO(students.getCV(students.getOne(id)))

    override fun getCVItem(id:Long, cvId: Long) = CVItemDTO(students.getCVItem(students.getCV(students.getOne(id)), id))

    override fun addCVItem(id: Long, cvId: Long) = students.addCVItem(students.getOne(id), students.getCVItem(students.getCV(students.getOne(id)), id))

    override fun editCVItem(id: Long, cvId: Long, cvItem: CVItemDTO) = students.editCVItem(students.getOne(id), students.getCVItem(students.getCV(students.getOne(id)), id), CVItemDAO(cvItem, c))

    override fun deleteCVItem(id: Long, cvId: Long) = students.deleteCVItem(students.getOne(id), students.getCVItem(students.getCV(students.getOne(id)), id))

}