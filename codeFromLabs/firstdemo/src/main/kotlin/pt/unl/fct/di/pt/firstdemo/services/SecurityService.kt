package pt.unl.fct.di.pt.firstdemo.services

import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import pt.unl.fct.di.pt.firstdemo.model.ApplicationRepository

@Component
class SecurityService(val applications: ApplicationRepository) {

    fun canAddApplication( user: UserDAO){
        //only Student role
    }

    fun canEditApplication( student: StudentDAO, applicationId: Long){
        val application = applications.findById(applicationId).orElseThrow{
            NotFoundException("chuj")
        }
        //TODO - this should not throw any exception, but otherwise function contains() can't be called
        application!=null && student.applications.contains(application)
        //and has a role Student
    }

    fun canDeleteApplication( student: StudentDAO, applicationId: Long){
        canEditApplication(student,applicationId)
        //and has a role Student
    }

    //TODO------------
    //---------------- two methods or one with UserDAO, but it's more complicated - to be decided
    fun canGetApplication( student: StudentDAO, applicationId: Long){
        canEditApplication(student,applicationId)
        //and has a role Student
    }

    fun canGetApplication( reviewer: ReviewerDAO, applicationId: Long){
        val application = applications.findById(applicationId)


        //and has a role Student
    }
    //---------------

    fun canGetStudentApplications( student: StudentDAO){
        
        //and has a role Student
    }

    fun canGetGrantApplications( call: GrantCallDAO){

    }



}