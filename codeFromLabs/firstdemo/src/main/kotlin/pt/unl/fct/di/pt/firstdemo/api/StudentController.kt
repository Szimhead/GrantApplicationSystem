package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.*

@RestController
class StudentController(val studs: StudentService, val gc: GrantCallService, val cvReqs: CVRequirementService, val users: UserService): StudentAPI {

    override fun getAll() = studs.getAll().map { UserDTO(it) }

    override fun getOne(id:Long) = UserDTO(studs.getOne(id))

    override fun deleteStudent(id: Long) {
        val student = studs.getOne(id)
        studs.deleteStudent(student, users.findUser(student.email))
    }

    override fun editStudent(id:Long, student: UserDTO) = studs.editStudent(studs.getOne(id), UserDAO.StudentDAO(student))

    override fun getApplications(id: Long) = studs.getApplicationsFromStudent(studs.getOne(id)).map { ApplicationDTO(it) }.toMutableSet()

    override fun getCV(id:Long) = CVDTO(studs.getStudentCV(studs.getOne(id)))

    override fun getCVItem(id:Long, cvId: Long) = CVItemDTO(studs.getCVItemFromStudentCV(studs.getStudentCV(studs.getOne(id)), cvId))

    override fun addCVItem(id: Long, cvItem: CVItemDTO) {
        val cv = studs.getStudentCV(studs.getOne(id))
        studs.addCVItem(cv, CVItemDAO(cvItem, cvReqs.getOne(cvItem.cvReqId), cv))
    }

    override fun editCVItem(id: Long, cvItem: CVItemDTO) {
        val cv = studs.getStudentCV(studs.getOne(id))
        studs.editCVItem(studs.getCVItemFromStudentCV(cv, cvItem.id), CVItemDAO(cvItem))
    }

    override fun deleteCVItem(id: Long, cvId: Long) {
        val cv = studs.getStudentCV(studs.getOne(id))
        studs.deleteCVItem(cv, studs.getCVItemFromStudentCV(cv, cvId))
    }

}