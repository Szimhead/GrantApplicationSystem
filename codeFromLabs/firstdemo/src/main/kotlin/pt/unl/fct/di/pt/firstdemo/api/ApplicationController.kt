package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.*

@RestController
class ApplicationController(val apps: ApplicationService, val reviewers: ReviewerService, val dataItems: DataItemService): ApplicationAPI {

    @PreAuthorize("hasRole({'ADMIN'})")
    override fun getAll() = apps.getAll().map { ApplicationDTO(it) }

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canGetApplication(principal,#id) and (hasRole({'REVIEWER'}) or hasRole({'STUDENT'}) or hasRole({'CHAIR'})))")
    override fun getOne(id:Long) = ApplicationDTO(apps.getOne(id))

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canEditApplication(principal,#id) and hasRole({'STUDENT'}))")
    override fun editApplication(id:Long, app: ApplicationDTO) = apps.editApplication(apps.getOne(id), ApplicationDAO(app))

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canGetReviewsFromApplication(principal,#id) and hasRole({'CHAIR'}))")
    override fun getAllReviewsFromApplication(id:Long) = apps.getAllReviewsFromApplication(apps.getOne(id)).map { ReviewDTO(it) }

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canGetReview(principal,#reviewId) and (hasRole({'REVIEWER'}) or hasRole({'STUDENT'}) or hasRole({'CHAIR'})))")
    override fun getOneReview(id:Long, reviewId:Long) = ReviewDTO(apps.getOneReview(apps.getOne(id), reviewId))

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canDeleteReview(principal,#reviewId) and (hasRole({'REVIEWER'}) or hasRole({'CHAIR'})))")
    override fun deleteReview(id:Long, reviewId: Long)  {
        val app = apps.getOne(id)
        apps.deleteReview(app, apps.getOneReview(app, reviewId))
    }

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canEditReview(principal,#review.id) and (hasRole({'REVIEWER'}) or hasRole({'CHAIR'})))")
    override fun editReview(id:Long, review:ReviewDTO) {
        val app = apps.getOne(id)
        apps.editReview(app, apps.getOneReview(app, review.id), ReviewDAO(review))
    }

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canAddReview(principal,#review.id) and (hasRole({'REVIEWER'}) or hasRole({'CHAIR'})))")
    override fun addReview(id:Long, review:ReviewDTO) {
        val app = apps.getOne(id)
        apps.addReview(app, ReviewDAO(review, app, reviewers.getOne(review.reviewerId)))
    }

    override fun getAllAnswers(id:Long) = apps.getAllAnswers(apps.getOne(id)).map { AnswerDTO(it) }

    override fun getOneAnswer(id:Long, answerId:Long) = AnswerDTO(apps.getOneAnswer(apps.getOne(id), answerId))

    override fun addAnswer(id: Long, answer:AnswerDTO) {
        val app = apps.getOne(id)
        apps.addAnswer(app, AnswerDAO(answer, dataItems.getOne(answer.dataItemId), app))
    }

    override fun editAnswer(id:Long, answer:AnswerDTO) {
        val app = apps.getOne(id)
        apps.editAnswer(app, apps.getOneAnswer(app, answer.id), AnswerDAO(answer))
    }

    override fun deleteAnswer(id:Long, answerId:Long) {
        val app = apps.getOne(id)
        apps.deleteAnswer(app, apps.getOneAnswer(app, answerId))
    }
}
