package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.ReviewerService

@RestController
class ReviewerController(val reviewers: ReviewerService): ReviewerAPI {

    override fun getAll() = reviewers.getAll()

    override fun getOne(reviewerNr:Long) = reviewers.getOne(reviewerNr)

    override fun deleteReviewer(reviewerNr: Long) = reviewers.deleteReviewer(reviewerNr)

    override fun editReviewer(reviewerNr: Long) = reviewers.updateReviewer(reviewerNr)

    /* panel handling */
    override fun getPanels(reviewerNr: Long) = reviewers.getPanels(reviewerNr)

    override fun getOnePanel(reviewerNr: Long, p_id:Long) = reviewers.getOnePanel(reviewerNr, p_id)

    /* reviews handling */
    override fun getReviews(reviewerNr: Long) = reviewers.getReviews(reviewerNr)

    override fun getOneReview(reviewerNr: Long, r_id:Long) = reviewers.getOneReview(reviewerNr, r_id)
}