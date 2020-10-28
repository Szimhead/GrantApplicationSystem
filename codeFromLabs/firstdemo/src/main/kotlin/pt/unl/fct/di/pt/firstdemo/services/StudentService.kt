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

    fun deleteStudent(studentNr:Long) {
        val student = students.findById(studentNr).orElseThrow{
            NotFoundException("Reviewer with $studentNr not found")
        }
        students.delete(student)
    }

    @Transactional
    fun editStudent(editedStudent: StudentDAO, student: StudentDAO){
        editedStudent.address=student.address
        editedStudent.email =student.email
        editedStudent.institution=student.institution
        editedStudent.name=student.name

        students.save(editedStudent)
    }

    //application handling
    @Transactional
    fun getApplications(studentNr: Long): MutableSet<ApplicationDAO> {
        val student = students.findById(studentNr).orElseThrow{
            NotFoundException("Student with $studentNr not found")
        }
        return student.applications
    }


    //CV handling
    fun getCV(student: StudentDAO): CVDAO {
        val cv = student.cv

        if(cv == null)
            throw NotFoundException("Application does not have a panel")
        else return cv
    }

    //cvItem handling
    fun getCVItem(cv: CVDAO, id: Long): CVItemDAO {
        val cvItem = cvItems.findById(id).orElseThrow{
            NotFoundException("CVItem with $id not found")
        }
        if (cv.CVItems.contains(cvItem)) return cvItem
        else throw NotFoundException("CVItem with $id doesn't exist in this CV")
    }

    @Transactional
    fun addCVItem(student: StudentDAO, cvItem: CVItemDAO) {
        cvItem.id = 0
        student.cv?.CVItems?.add(cvItem)
        students.save(student)
        cvItem.CV = student.cv!!
        cvItems.save(cvItem)
        cvs.save(student.cv!!) //but whyyyyyyy
    }

    fun editCVItem(student: StudentDAO, cvItemEdited: CVItemDAO, newCvItem: CVItemDAO) {
        if(student.cv?.CVItems?.contains(cvItemEdited)!!) { // safe calls
            cvItemEdited.name = newCvItem.name
            cvItemEdited.value = newCvItem.value
            cvItemEdited.dataType = newCvItem.dataType
            cvItems.save(newCvItem)
            cvs.save(student.cv!!)
            students.save(student)
        }else throw NotFoundException("CVItem with $cvItemId not found for the student $studentNr")
    }

    fun deleteCVItem(student: StudentDAO, cvItem: CVItemDAO) {
        if(student.cv?.CVItems?.contains(cvItem)!!) {
            cvItems.delete(cvItem)
        }else
            throw NotFoundException("CVItem with $cvItemId not found for the student $studentNr")
    }
}