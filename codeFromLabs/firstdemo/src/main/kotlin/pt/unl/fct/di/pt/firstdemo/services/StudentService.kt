package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.*
import javax.transaction.Transactional

@Service
class StudentService (val students: StudentRepository, val applications: ApplicationRepository, val cvItems: CVItemRepository, val users: UserRepository){
    fun getAll() : Iterable<UserDAO.StudentDAO> = students.findAll()

    fun getOne(id:Long): UserDAO.StudentDAO = students.findById(id).orElseThrow{
        NotFoundException("Student with $id not found")
    }

    fun deleteStudent(student: UserDAO.StudentDAO, user: UserDAO) {
        users.delete(user)
        students.delete(student)
    }

    @Transactional
    fun editStudent(editedStudent: UserDAO.StudentDAO, student: UserDAO.StudentDAO){
        editedStudent.address = student.address
        editedStudent.institution = student.institution
        editedStudent.name = student.name
        students.save(editedStudent)
    }

    //application handling
    @Transactional
    fun getApplicationsFromStudent(student: UserDAO.StudentDAO): MutableSet<ApplicationDAO> {
        return student.applications
    }


    //CV handling
    fun getStudentCV(student: UserDAO.StudentDAO): CVDAO {
        val cv = student.cv

        if(cv == null)
            throw NotFoundException("Student does not have a cv")
        else
            return cv
    }

    //cvItem handling
    fun getCVItemFromStudentCV(cv: CVDAO, cvItemId: Long): CVItemDAO {
        val cvItem = cvItems.findById(cvItemId).orElseThrow{
            NotFoundException("CVItem with $cvItemId not found")
        }
        if (cv.CVItems.contains(cvItem))
            return cvItem
        else
            throw NotFoundException("CVItem with $cvItemId doesn't exist in this CV")
    }

    @Transactional
    fun addCVItem(cv: CVDAO, cvItem: CVItemDAO) {
        cv.CVItems.add(cvItem)
        cvItem.CVRequirement.CVItems.add(cvItem)

        cvItems.save(cvItem)
    }

    fun editCVItem(cvItemEdited: CVItemDAO, newCvItem: CVItemDAO) {
        cvItemEdited.name = newCvItem.name
        cvItemEdited.value = newCvItem.value
        cvItemEdited.dataType = newCvItem.dataType
    }

    fun deleteCVItem(cv: CVDAO, cvItem: CVItemDAO) {
       cv.CVItems.remove(cvItem)
       cvItems.delete(cvItem)
    }
}