package pt.unl.fct.di.pt.firstdemo.services

import javassist.NotFoundException
import org.springframework.stereotype.Component
import pt.unl.fct.di.pt.firstdemo.model.ApplicationRepository
import pt.unl.fct.di.pt.firstdemo.model.GrantCallRepository
import pt.unl.fct.di.pt.firstdemo.model.ReviewRepository

@Component("SecurityService")
class SecurityService(val applications: ApplicationRepository, val reviews: ReviewRepository, val calls: GrantCallRepository) {

    fun canEditApplication( student: StudentDAO, applicationId: Long): Boolean {
        val application = applications.findById(applicationId).orElse(null)
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
    }//student, reviewer, chair, sponsor

    fun canGetApplication(reviewer: ReviewerDAO, applicationId: Long): Boolean {
        val application = applications.findById(applicationId).orElse(null)

        return application != null && reviewer.panels.contains(application.grantCall.panel)
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
        val review = reviews.findById(reviewId).orElse(ReviewDAO())
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

    fun canGetReviewsFromApplication(reviewer: ReviewerDAO, applicationId: Long):Boolean{
        val application = applications.findById(applicationId).orElse(null)
        return application != null && reviewer == application.grantCall.panel?.chair
    }

    //GrantCall Security
    fun canAddGrantCall() {
        // has role Sponsor
    }

    fun canEditGrantCall(sponsor: SponsorDAO, callId: Long): Boolean {
        val call = calls.findById(callId).orElse(GrantCallDAO())
        return sponsor.grantCalls.contains(call)
        // has role Sponsor
    }

    fun canDeleteGrantCall(sponsor: SponsorDAO, callId: Long): Boolean {
        return canEditGrantCall(sponsor,callId)
    }

    fun canGetGrantCallSp(sponsor: SponsorDAO, callId: Long): Boolean {
        return canEditGrantCall(sponsor,callId)
    }//sponsor

    fun canGetGrantCallSt(){
        //has role Student
    }//student

    fun canGetGrantCallRv(){
        //has role Reviewer
    }

    fun canAddPanelChair(sponsor: SponsorDAO, id: Long):Boolean{
        val call = calls.findById(id).orElse(null)
        return call!=null && sponsor.grantCalls.contains(call)
    }

    fun canEditPanelChair(sponsor: SponsorDAO, id: Long):Boolean{
        return canAddPanelChair(sponsor,id)
    }

    fun canDeletePanelChair(sponsor: SponsorDAO, id: Long):Boolean{
        return canAddPanelChair(sponsor,id)
    }

    fun canGetReviewersFromGrantCall(sponsor: SponsorDAO, id: Long):Boolean{
        return canAddPanelChair(sponsor,id)
    }

    fun canGetReviewersFromGrantCall(chair: ReviewerDAO, id: Long):Boolean{
        val call = calls.findById(id).orElse(null)
        return call != null && call.panel?.chair == chair
    }

    fun canAddReviewersToGrantCall(chair: ReviewerDAO, id: Long):Boolean{
        return canAddReviewersToGrantCall(chair,id)
    }

    fun canDeleteReviewersFromGrantCall(chair: ReviewerDAO, id: Long):Boolean{
        return canAddReviewersToGrantCall(chair,id)
    }

    //reviewer

}