package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.ReviewerDAO
import pt.unl.fct.di.pt.firstdemo.services.ReviewerService

@RestController
class ReviewerController(val reviewers: ReviewerService): ReviewerAPI {

    override fun getAll() = reviewers.getAll().map { UserDTO(it) }

    override fun getOne(reviewerNr:Long) = UserDTO(reviewers.getOne(reviewerNr))

    override fun addReviewer(reviewer: UserDTO) = reviewers.addReviewer(ReviewerDAO(reviewer))

    override fun deleteReviewer(reviewerNr: Long) = reviewers.deleteReviewer(reviewerNr)

    override fun editReviewer(reviewerNr: Long, reviewer: UserDTO) = reviewers.updateReviewer(reviewerNr, ReviewerDAO(reviewer))

    /* panel handling */
    override fun getPanels(reviewerNr: Long) = reviewers.getPanels(reviewerNr).map { PanelDTO(it) }

    override fun getOnePanel(reviewerNr: Long, panelId:Long) = PanelDTO(reviewers.getOnePanel(reviewerNr, panelId))

    /* reviews handling */
    override fun getReviews(reviewerNr: Long) = reviewers.getReviews(reviewerNr).map { ReviewDTO(it) }

    override fun getOneReview(reviewerNr: Long, reviewId:Long) = ReviewDTO(reviewers.getOneReview(reviewerNr, reviewId))
}