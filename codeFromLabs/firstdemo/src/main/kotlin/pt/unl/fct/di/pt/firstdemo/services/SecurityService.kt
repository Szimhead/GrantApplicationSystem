package pt.unl.fct.di.pt.firstdemo.services

import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import pt.unl.fct.di.pt.firstdemo.model.ApplicationRepository

@Component
class SecurityService(val applications: ApplicationRepository) {

    fun canAddApplication(user: UserDAO){
        //only Student role
    }

    fun canEditApplication( student: StudentDAO, applicationId: Long): Boolean {
        val application = applications.findById(applicationId).orElseThrow{
            NotFoundException("chuj")
        }
        //TODO - this should not throw any exception, but otherwise function contains() can't be called
        return application!=null && student.applications.contains(application)
        //and has a role Student
    }

    fun canDeleteApplication(student: StudentDAO, applicationId: Long){
        return canEditApplication(student,applicationId)
        //and has a role Student
    }

    //TODO------------
    //---------------- two methods or one with UserDAO, but it's more complicated - to be decided
    fun canGetApplication( student: StudentDAO, applicationId: Long){
        return canEditApplication(student,applicationId)
        //and has a role Student
    }

    fun canGetApplication(reviewer: ReviewerDAO, applicationId: Long): Boolean {
        val application = applications.findById(applicationId)

        return application !=null &&  reviewer.panels.contains(application.panel)
        //and has a role Student
    }
    //---------------

    fun canGetStudentApplications(student: StudentDAO){

        //has a role Student and is the owner of the applications
    }

    fun canGetGrantApplications(sponsor: SponsorDAO, call: GrantCallDAO): Boolean {
        return call.sponsor.equals(sponsor)
        //has a role sponsor
    }

    fun canGetGrantApplications(reviewer: ReviewerDAO, call: GrantCallDAO): Boolean? {
        return call.panel !=null && call.panel!!.reviewers !=null && call.panel?.reviewers?.contains(reviewer)!!
        //has a role reviewer
    }



}