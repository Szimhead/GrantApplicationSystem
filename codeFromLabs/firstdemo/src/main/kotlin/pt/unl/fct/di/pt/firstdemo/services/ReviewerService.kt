package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.PanelRepository
import pt.unl.fct.di.pt.firstdemo.model.ReviewRepository
import pt.unl.fct.di.pt.firstdemo.model.ReviewerRepository
import javax.transaction.Transactional

@Service
class ReviewerService (val reviewers: ReviewerRepository, val panels: PanelRepository, val reviews: ReviewRepository) {
    fun getAll(): Iterable<ReviewerDAO> = reviewers.findAll()

    fun getOne(id:Long): ReviewerDAO = reviewers.findById(id).orElseThrow{
        NotFoundException("Reviewer with $id not found")
    }
    
    fun addReviewer(reviewer: ReviewerDAO) {
        reviewers.save(reviewer)
    }

    fun deleteReviewer(reviewer: ReviewerDAO) {
        reviewers.delete(reviewer)
    }

    @Transactional
    fun editReviewer(editedRev: ReviewerDAO, newRev: ReviewerDAO) {
        editedRev.address = newRev.address
        editedRev.email = newRev.email
        editedRev.institution = newRev.institution
        editedRev.name = newRev.name
        reviewers.save(editedRev)
    }

    /* panel handling */
    @Transactional
    fun getPanelsFromReviewer(reviewer: ReviewerDAO): Iterable<PanelDAO> {
        return reviewer.panels
    }

    @Transactional
    fun getOnePanel(reviewer: ReviewerDAO, panelId:Long) :PanelDAO{
        val panel = panels.findById(panelId).orElseThrow{
            NotFoundException("Panel with $panelId not found")
        }

        if(reviewer.panels.contains(panel))
            return panel
        else
            throw NotFoundException("Panel with $panelId not found")
    }

    /* reviews handling */
    @Transactional
    fun getReviewsFromReviewer(reviewer: ReviewerDAO): Iterable<ReviewDAO>  {
        return reviewer.reviews
    }

    @Transactional
    fun getOneReview(reviewer: ReviewerDAO, reviewId:Long) : ReviewDAO {
        val review = reviews.findById(reviewId).orElseThrow{
            NotFoundException("Panel with $reviewId not found")
        }
        if(reviewer.reviews.contains(review))
            return review
        else
            throw NotFoundException("Panel with $reviewId not found")
    }
}