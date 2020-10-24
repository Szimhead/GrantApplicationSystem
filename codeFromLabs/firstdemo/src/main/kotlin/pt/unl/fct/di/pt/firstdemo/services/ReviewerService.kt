package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.boot.context.properties.bind.handler.NoUnboundElementsBindHandler
import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.PanelDTO
import pt.unl.fct.di.pt.firstdemo.api.ReviewDTO
import pt.unl.fct.di.pt.firstdemo.api.UserDTO
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.PanelRepository
import pt.unl.fct.di.pt.firstdemo.model.ReviewerRepository
import java.util.*
import javax.persistence.*

@Service
class ReviewerService (val reviewers: ReviewerRepository, val panels: PanelRepository, val reviews: ReviewerRepository) {
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
    fun getPanels(reviewerNr: Long): Iterable<PanelDAO> {
        val reviewer = reviewers.findById(reviewerNr).orElseThrow{
            NotFoundException("Reviewer with $reviewerNr not found")
        }
        return reviewer.panels
    }

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
    fun getReviews(reviewerNr: Long): Iterable<ReviewDAO>  {
        val reviewer = reviewers.findById(reviewerNr).orElseThrow{
            NotFoundException("Reviewer with $reviewerNr not found")
        }
        return reviewer.reviews
    }

    fun getOneReview(reviewerNr: Long, reviewId:Long): ReviewDAO {
//        val reviewer = reviewers.findById(reviewerNr).orElseThrow{
//            NotFoundException("Reviewer with $reviewerNr not found")
//        }
//        val review = reviews.findById(reviewId).orElseThrow{
//            NotFoundException("Panel with $reviewId not found")
//        }
//        if(reviewer.reviews.contains(review))
//            return review
//        else throw NotFoundException("Panel with $reviewId not found")
        //TODO: works in getOnePanel, displays errors in this one, idk y
    }
}