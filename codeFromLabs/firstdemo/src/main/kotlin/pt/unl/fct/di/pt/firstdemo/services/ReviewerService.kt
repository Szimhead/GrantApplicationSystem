package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.PanelRepository
import pt.unl.fct.di.pt.firstdemo.model.ReviewRepository
import pt.unl.fct.di.pt.firstdemo.model.ReviewerRepository
import pt.unl.fct.di.pt.firstdemo.model.UserRepository
import javax.transaction.Transactional

@Service
class ReviewerService (val reviewers: ReviewerRepository, val panels: PanelRepository, val reviews: ReviewRepository, val users: UserRepository) {
    fun getAll(): Iterable<UserDAO.ReviewerDAO> = reviewers.findAll()

    fun getOne(id:Long): UserDAO.ReviewerDAO = reviewers.findById(id).orElseThrow{
        NotFoundException("Reviewer with $id not found")
    }
    
    fun addReviewer(reviewer: UserDAO.ReviewerDAO) {
        reviewers.save(reviewer)
    }

    fun deleteReviewer(reviewer: UserDAO.ReviewerDAO, user: UserDAO) {
        users.delete(user)
        reviewers.delete(reviewer)
    }

    @Transactional
    fun editReviewer(editedRev: UserDAO.ReviewerDAO, newRev: UserDAO.ReviewerDAO) {
        editedRev.address = newRev.address
        editedRev.institution = newRev.institution
        editedRev.name = newRev.name
        reviewers.save(editedRev)
    }

    /* panel handling */
    @Transactional
    fun getPanelsFromReviewer(reviewer: UserDAO.ReviewerDAO): Iterable<PanelDAO> {
        return reviewer.panels
    }

    @Transactional
    fun getOnePanel(reviewer: UserDAO.ReviewerDAO, panelId:Long) :PanelDAO{
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
    fun getReviewsFromReviewer(reviewer: UserDAO.ReviewerDAO): Iterable<ReviewDAO>  {
        return reviewer.reviews
    }

    @Transactional
    fun getOneReview(reviewer: UserDAO.ReviewerDAO, reviewId:Long) : ReviewDAO {
        val review = reviews.findById(reviewId).orElseThrow{
            NotFoundException("Panel with $reviewId not found")
        }
        if(reviewer.reviews.contains(review))
            return review
        else
            throw NotFoundException("Panel with $reviewId not found")
    }
}