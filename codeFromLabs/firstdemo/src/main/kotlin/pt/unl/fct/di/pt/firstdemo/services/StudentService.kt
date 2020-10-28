package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.ApplicationRepository
import pt.unl.fct.di.pt.firstdemo.model.CVItemRepository
import pt.unl.fct.di.pt.firstdemo.model.CVRepository
import pt.unl.fct.di.pt.firstdemo.model.StudentRepository
import javax.transaction.Transactional

@Service
class StudentService (val students: StudentRepository, val applications: ApplicationRepository, cvItems: CVItemRepository, cvs: CVRepository){
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

    @Transactional
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
    @Transactional
    fun getApplications(studentNr: Long): Iterable<ApplicationDAO> {
        val student = students.findById(studentNr).orElseThrow{
            NotFoundException("Student with $studentNr not found")
        }
        return student.applications
    }

    fun getOneApplication(studentNr: Long, id:Long):ApplicationDAO{
        val student = students.findById(studentNr).orElseThrow{
            NotFoundException("Student with $studentNr not found")
        }
        val application = applications.findById(id).orElseThrow{
            NotFoundException("Application with $id not found")
        }
        if(student.applications.contains(application))
            return application
        else throw NotFoundException("Application with $id not found")
    }

    //CV handling
    fun getCV(studentNr:Long): CVDAO {
        val student = students.findById(studentNr).orElseThrow{
            NotFoundException("Student with $studentNr not found")
        }
        return student.cv ?: throw NotFoundException("CV not found")
    }

    //cvItem handling
    @Transactional
    fun addCVItem(studentNr: Long, cvItem: CVItemDAO) {
        val student = students.findById(studentNr).orElseThrow{
            NotFoundException("Student with $studentNr not found")
        }
        cvItem.id = 0
        student.cv?.CVItems?.add(cvItem)
        students.save(student)
        cvItem.CV = student.cv!!
        cvItems.save(cvItem)
        cvs.save(student.cv) //but whyyyyyyy
    }

    fun editCVItem(studentNr: Long, cvItemId: Long, cvItem: CVItemDAO) {
        val student = students.findById(studentNr).orElseThrow{
            NotFoundException("Student with $studentNr not found")
        }
        val cvItemEdited = cvItems.findById(cvItemId).orElseThrow{
            NotFoundException("CVItem with $cvItemId not found")
        }
        if(student.cv?.CVItems?.contains(cvItemEdited)!!) { // safe calls
            cvItemEdited.name = cvItem.name
            cvItemEdited.value = cvItem.value
            cvItemEdited.dataType = cvItem.dataType
            cvItems.save(cvItem)
            cvs.save(student.cv)
            students.save(student)
        }else throw NotFoundException("CVItem with $cvItemId not found for the student $studentNr")
    }

    fun deleteCVItem(studentNr: Long, cvItemId: Long) {
        val student = students.findById(studentNr).orElseThrow{
            NotFoundException("Student with $studentNr not found")
        }
        val cvItemDeleted = cvItems.findById(cvItemId).orElseThrow{
            NotFoundException("CVItem with $cvItemId not found")
        }
        if(student.cv?.CVItems?.contains(cvItemDeleted)!!) {
            cvItems.delete(cvItemDeleted)
        }else
            throw NotFoundException("CVItem with $cvItemId not found for the student $studentNr")
    }
}