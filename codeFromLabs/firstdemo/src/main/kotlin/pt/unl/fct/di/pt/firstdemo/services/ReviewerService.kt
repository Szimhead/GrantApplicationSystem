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
        reviewer.id=0
        reviewers.save(reviewer)
    }

    fun deleteReviewer(reviewerNr:Long) {
        val reviewer = reviewers.findById(reviewerNr).orElseThrow{
            NotFoundException("Reviewer with $reviewerNr not found")
        }
        reviewers.delete(reviewer)
    }

    @Transactional
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
    @Transactional
    fun getPanels(reviewerNr: Long): Iterable<PanelDAO> {
        val reviewer = reviewers.findById(reviewerNr).orElseThrow{
            NotFoundException("Reviewer with $reviewerNr not found")
        }
        return reviewer.panels
    }

    @Transactional
    fun getOnePanel(reviewerNr: Long, panelId:Long) :PanelDAO{
        val reviewer = reviewers.findById(reviewerNr).orElseThrow{
            NotFoundException("Reviewer with $reviewerNr not found")
        }
        val panel = panels.findById(panelId).orElseThrow{
            NotFoundException("Panel with $panelId not found")
        }
        if(reviewer.panels.contains(panel))
            return panel
        else throw NotFoundException("Panel with $panelId not found")
    }

    /* reviews handling */
    @Transactional
    fun getReviews(reviewerNr: Long): Iterable<ReviewDAO>  {
        val reviewer = reviewers.findById(reviewerNr).orElseThrow{
            NotFoundException("Reviewer with $reviewerNr not found")
        }
        return reviewer.reviews
    }

    @Transactional
    fun getOneReview(reviewerNr: Long, reviewId:Long) : ReviewDAO {
        val reviewer = reviewers.findById(reviewerNr).orElseThrow{
            NotFoundException("Reviewer with $reviewerNr not found")
        }
        val review = reviews.findById(reviewId).orElseThrow{
            NotFoundException("Panel with $reviewId not found")
        }
        if(reviewer.reviews.contains(review))
            return review
        else throw NotFoundException("Panel with $reviewId not found")
    }
}