package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.ReviewerDAO
import pt.unl.fct.di.pt.firstdemo.services.ReviewerService

@RestController
class ReviewerController(val revs: ReviewerService): ReviewerAPI {

    override fun getAll() = revs.getAll().map { UserDTO(it) }

    override fun getOne(id:Long) = UserDTO(revs.getOne(id))

    override fun deleteReviewer(id: Long) = revs.deleteReviewer(revs.getOne(id))

    override fun editReviewer(id: Long, reviewer: UserDTO) = revs.editReviewer(revs.getOne(id), ReviewerDAO(reviewer))

    /* panel handling */
    override fun getPanels(id: Long) = revs.getPanelsFromReviewer(revs.getOne(id)).map { PanelDTO(it) }

    override fun getOnePanel(id: Long, panelId:Long) = PanelDTO(revs.getOnePanel(revs.getOne(id), panelId))

    /* reviews handling */
    override fun getReviews(id: Long) = revs.getReviewsFromReviewer(revs.getOne(id)).map { ReviewDTO(it) }

    override fun getOneReview(id: Long, reviewId:Long) = ReviewDTO(revs.getOneReview(revs.getOne(id), reviewId))
}