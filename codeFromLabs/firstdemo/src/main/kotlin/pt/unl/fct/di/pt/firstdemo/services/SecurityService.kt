package pt.unl.fct.di.pt.firstdemo.services

import com.sun.org.apache.xpath.internal.operations.Bool
import javassist.NotFoundException
import org.springframework.stereotype.Component
import pt.unl.fct.di.pt.firstdemo.model.ApplicationRepository
import pt.unl.fct.di.pt.firstdemo.model.ReviewRepository

@Component
class SecurityService(val applications: ApplicationRepository, val reviews: ReviewRepository) {

    //Application Security
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

    fun canDeleteApplication(student: StudentDAO, applicationId: Long): Boolean {
        return canEditApplication(student,applicationId)
        //and has a role Student
    }

    //TODO------------
    //---------------- two methods or one with UserDAO, but it's more complicated - to be decided
    fun canGetApplication( student: StudentDAO, applicationId: Long): Boolean {
        return canEditApplication(student,applicationId)
        //and has a role Student
    }

    fun canGetApplication(reviewer: ReviewerDAO, applicationId: Long): Boolean {
        val application = applications.findById(applicationId).orElseThrow {
            NotFoundException("chuj")
        }

        return reviewer.panels.contains(application.grantCall.panel) // ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! NAILED IT
        //and has a role Student
    }
    //---------------

    fun canGetStudentApplications(student: StudentDAO){

        //has a role Student and is the owner of the applications
    }

    fun canGetGrantApplications(sponsor: SponsorDAO, call: GrantCallDAO): Boolean {
        return call.sponsor == sponsor
        //has a role sponsor
    }

    fun canGetGrantApplications(reviewer: ReviewerDAO, call: GrantCallDAO): Boolean {
        return call.panel !=null && call.panel?.reviewers?.contains(reviewer)!!
        //has a role reviewer
    }

    //Review Security
    fun canAddReview(reviewer: ReviewerDAO, application: ApplicationDAO): Boolean {
        return reviewer.panels.contains(application.grantCall.panel)
        //and has role Reviewer
    }

    fun canEditReview(reviewer: ReviewerDAO, reviewId: Long): Boolean {
        val review = reviews.findById(reviewId).orElseThrow {
            NotFoundException("chuj")
        }
        return reviewer == review.reviewer
        //and has role Reviewer
    }

    fun canDeleteReview(reviewer: ReviewerDAO, reviewId: Long): Boolean {
        return canEditReview(reviewer, reviewId)
        //and has role Reviewer
    }

    fun canGetReview(reviewer: ReviewerDAO, reviewId: Long): Boolean {
        return canEditReview(reviewer, reviewId)
        //and has role Reviewer
    }

    fun canGetReview(student: StudentDAO, reviewId: Long): Boolean {
        val review = reviews.findById(reviewId).orElseThrow {
            NotFoundException("chuj")
        }
        return student.applications.contains(review.application)
        //and has role Reviewer
    }
}