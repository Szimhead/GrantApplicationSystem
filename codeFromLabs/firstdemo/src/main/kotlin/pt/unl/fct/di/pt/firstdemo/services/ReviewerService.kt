package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.PanelDTO
import pt.unl.fct.di.pt.firstdemo.api.ReviewDTO
import pt.unl.fct.di.pt.firstdemo.api.UserDTO
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.ReviewerRepository
import javax.persistence.*

@Service
class ReviewerService (val reviewers: ReviewerRepository) {
    fun getAll(): Iterable<ReviewerDAO> = reviewers.findAll()

    fun getOne(id:Long): ReviewerDAO = reviewers.findById(id).orElseThrow{
        NotFoundException("Reviewer with $id not found")
    }

    fun addReviewer(reviewer: ReviewerDAO) {
        reviewers.save(reviewer)
    }

    fun deleteReviewer(reviewerNr:Long) {
        val reviewer = reviewers.findById(reviewerNr).orElseThrow{
            NotFoundException("Reviewer with $reviewerNr not found")
        }
        reviewers.delete(reviewer);
    }

    fun updateReviewer(reviewerNr: Long, reviewer: ReviewerDAO) {
        val editedReviewer = reviewers.findById(reviewerNr).orElseThrow{
            NotFoundException("Reviewer with $reviewerNr not found")
        };
        editedReviewer.address=reviewer.address
        editedReviewer.email =reviewer.email
        editedReviewer.institution=reviewer.institution
        editedReviewer.name=reviewer.name

        reviewers.save(editedReviewer)
    }

    /* panel handling */
    fun getPanels(reviewerNr: Long) = listOf<PanelDTO>(PanelDTO(0))

    fun getOnePanel(reviewerNr: Long, p_id:Long) = PanelDTO(1)

    /* reviews handling */
    fun getReviews(reviewerNr: Long) = listOf<ReviewDTO>(ReviewDTO(1, true, "very nice", 0))

    fun getOneReview(reviewerNr: Long, r_id:Long) = ReviewDTO(2, false, "not very nice", 0)

}