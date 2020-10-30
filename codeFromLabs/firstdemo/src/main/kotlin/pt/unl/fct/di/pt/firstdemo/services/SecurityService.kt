package pt.unl.fct.di.pt.firstdemo.services

import javassist.NotFoundException
import org.h2.engine.User
import org.springframework.stereotype.Component
import org.w3c.dom.UserDataHandler
import pt.unl.fct.di.pt.firstdemo.model.ApplicationRepository
import pt.unl.fct.di.pt.firstdemo.model.GrantCallRepository
import pt.unl.fct.di.pt.firstdemo.model.ReviewRepository

@Component("SecurityService")
class SecurityService(val applications: ApplicationRepository, val reviews: ReviewRepository, val calls: GrantCallRepository) {

    fun canEditApplication( user: UserDAO, applicationId: Long): Boolean {
        val application = applications.findById(applicationId).orElse(null)
        when(user){
            is UserDAO.ReviewerDAO -> { return false }
            is UserDAO.StudentDAO -> { return application!=null && user.applications.contains(application) }
            is UserDAO.SponsorDAO -> { return false }
        }
        return false
    }

    fun canDeleteApplication(user: UserDAO, applicationId: Long): Boolean {
        return canEditApplication(user,applicationId)
        //and has a role Student
    }

    fun canGetApplication( user: UserDAO, applicationId: Long): Boolean {
        val application = applications.findById(applicationId).orElse(null)
        when(user){
            is UserDAO.ReviewerDAO -> { return application != null && user.panels.contains(application.grantCall.panel)}
            is UserDAO.StudentDAO -> { return canEditApplication(user,applicationId) }
            is UserDAO.SponsorDAO -> { return user.grantCalls.contains(application.grantCall) }
        }
        return false
        //and has a role Student
    }//student, reviewer, chair, sponsor

    fun canGetGrantApplications(user: UserDAO, callId: Long): Boolean {
        val call = calls.findById(callId).orElse(null)
        when(user){
            is UserDAO.ReviewerDAO -> { return call.panel !=null && call.panel?.reviewers?.contains(user)!! }
            is UserDAO.StudentDAO -> { return false }
            is UserDAO.SponsorDAO -> { return user.grantCalls.contains(call) }
        }
        return false
        //has a role reviewer
    }

    //Review Security
    fun canAddReview(user: UserDAO, applicationId: Long): Boolean {
        val application = applications.findById(applicationId).orElse(null)
        when(user){
            is UserDAO.ReviewerDAO -> { return user.panels.contains(application.grantCall.panel) }
            is UserDAO.StudentDAO -> { return false }
            is UserDAO.SponsorDAO -> { return false }
        }
        return false
        //and has role Reviewer
    }

    fun canEditReview(user: UserDAO, reviewId: Long): Boolean {
        val review = reviews.findById(reviewId).orElse(ReviewDAO())
        when(user){
            is UserDAO.ReviewerDAO -> { return user == review.reviewer }
            is UserDAO.StudentDAO -> { return false }
            is UserDAO.SponsorDAO -> { return false }
        }
        return false
        //and has role Reviewer
    }

    fun canDeleteReview(user: UserDAO, reviewId: Long): Boolean {
        return canEditReview(user, reviewId)
        //and has role Reviewer
    }

    fun canGetReview(user: UserDAO, reviewId: Long): Boolean {
        val review = reviews.findById(reviewId).orElse(null)
        when(user){
            is UserDAO.ReviewerDAO -> { return canEditReview(user, reviewId) }
            is UserDAO.StudentDAO -> { return user.applications.contains(review.application) }
            is UserDAO.SponsorDAO -> { return false }
        }
        return false

        //and has role Reviewer
    }

    fun canGetReviewsFromApplication(user: UserDAO, applicationId: Long):Boolean{
        val application = applications.findById(applicationId).orElse(null)
        when(user){
            is UserDAO.ReviewerDAO -> { return application != null && user == application.grantCall.panel?.chair }
            is UserDAO.StudentDAO -> { return false }
            is UserDAO.SponsorDAO -> { return false }
        }
        return false
    }

    //GrantCall Security

    fun canEditGrantCall(user: UserDAO, callId: Long): Boolean {
        val call = calls.findById(callId).orElse(GrantCallDAO())
        when(user){
            is UserDAO.ReviewerDAO -> { return false }
            is UserDAO.StudentDAO -> { return false }
            is UserDAO.SponsorDAO -> { return user.grantCalls.contains(call) }
        }
        return false
        // has role Sponsor
    }

    fun canDeleteGrantCall(user: UserDAO, callId: Long): Boolean {
        return canEditGrantCall(user,callId)
    }

    fun canGetGrantCall(user: UserDAO, callId: Long): Boolean {
        return canEditGrantCall(user,callId)
    }//sponsor

    fun canAddPanelChair(user: UserDAO, id: Long):Boolean{
        val call = calls.findById(id).orElse(null)
        when(user){
            is UserDAO.ReviewerDAO -> { return false }
            is UserDAO.StudentDAO -> { return false }
            is UserDAO.SponsorDAO -> { return call!=null && user.grantCalls.contains(call) }
        }
        return false
    }

    fun canEditPanelChair(user: UserDAO, id: Long):Boolean{
        return canAddPanelChair(user,id)
    }

    fun canDeletePanelChair(user: UserDAO, id: Long):Boolean{
        return canAddPanelChair(user,id)
    }

    fun canGetReviewersFromGrantCall(user: UserDAO, id: Long):Boolean{
        val call = calls.findById(id).orElse(null)
        when(user){
            is UserDAO.ReviewerDAO -> { return call != null && call.panel?.chair == user }
            is UserDAO.StudentDAO -> { return false }
            is UserDAO.SponsorDAO -> { return canAddPanelChair(user,id)}
        }
        return false
    }

    fun canAddReviewersToGrantCall(chair: UserDAO, id: Long):Boolean{
        return canAddReviewersToGrantCall(chair,id)
    }

    fun canDeleteReviewersFromGrantCall(chair: UserDAO, id: Long):Boolean{
        return canAddReviewersToGrantCall(chair,id)
    }
    //reviewer

}
