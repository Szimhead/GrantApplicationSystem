package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.ApplicationRepository
import pt.unl.fct.di.pt.firstdemo.model.CVItemRepository
import pt.unl.fct.di.pt.firstdemo.model.CVRepository
import pt.unl.fct.di.pt.firstdemo.model.StudentRepository
import javax.transaction.Transactional

@Service
class StudentService (val students: StudentRepository, val applications: ApplicationRepository, val cvItems: CVItemRepository, val cvs: CVRepository){
    fun getAll() : Iterable<StudentDAO> = students.findAll()

    fun getOne(id:Long): StudentDAO = students.findById(id).orElseThrow{
        NotFoundException("Student with $id not found")
    }

    fun deleteStudent(student: StudentDAO) {
        students.delete(student)
    }

    @Transactional
    fun editStudent(editedStudent: StudentDAO, student: StudentDAO){
        editedStudent.address = student.address
        editedStudent.email = student.email
        editedStudent.institution = student.institution
        editedStudent.name = student.name
    }

    //application handling
    @Transactional
    fun getApplicationsFromStudent(student: StudentDAO): MutableSet<ApplicationDAO> {
        return student.applications
    }


    //CV handling
    fun getStudentCV(student: StudentDAO): CVDAO {
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