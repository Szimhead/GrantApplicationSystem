package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pt.unl.fct.di.pt.firstdemo.api.AnswerDTO
import pt.unl.fct.di.pt.firstdemo.api.ApplicationDTO
import pt.unl.fct.di.pt.firstdemo.api.ReviewDTO
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.AnswerRepository
import pt.unl.fct.di.pt.firstdemo.model.ApplicationRepository
import pt.unl.fct.di.pt.firstdemo.model.ReviewRepository
import pt.unl.fct.di.pt.firstdemo.model.ReviewerRepository
import java.util.*

@Service
class ApplicationService(val applications: ApplicationRepository, val reviews: ReviewRepository, val answers:AnswerRepository, val reviewers:ReviewerRepository) {

    fun getAll(): Iterable<ApplicationDAO> = applications.findAll()

    fun getOne(id:Long): ApplicationDAO = applications.findById(id).orElseThrow {
        NotFoundException("Application with $id not found")
    }

    fun deleteApplication(id: Long) = applications.deleteById(id)

    fun editApplication(id:Long, app:ApplicationDAO) {
        var editedApplication = applications.findById(id).orElseThrow() {
            NotFoundException("Application with $id not found")
        }
        editedApplication.submissionDate = app.submissionDate
        editedApplication.status = app.status
        editedApplication.grantCall = app.grantCall
        applications.save(editedApplication)
    }

    /* Review handling */
    fun getAllReviewsFromApplication(id: Long): Iterable<ReviewDAO> {
        val app = applications.findById(id).orElseThrow() {
            NotFoundException("Application with $id not found")
        }
        return app.reviews
    }

    fun addReview(id: Long, reviewerId: Long, review:ReviewDAO) {
        var app = applications.findById(id).orElseThrow() {
            NotFoundException("Application with $id not found")
        }
        var reviewer = reviewers.findById(reviewerId).orElseThrow() {
            NotFoundException("Reviewer with $reviewerId not found")
        }
        review.id = 0;
        reviews.save(review);

        app.reviews.add(review)
        reviewer.reviews.add(review)

        applications.save(app)
        reviewers.save(reviewer)
    }

    fun deleteReview(id:Long, review:ReviewDTO) {
        val app = applications.findById(id).orElseThrow() {
            NotFoundException("Application with $id not found")
        }
        applications.delete(app)
    }

    fun editReview(id:Long, review:ReviewDTO) {
        TODO("Not yet implemented, should this be in review service? just like get one review")
    }

    /* Answers handling */
    fun getAllAnswers(id:Long) : Iterable<AnswerDAO> {
        val app = applications.findById(id).orElseThrow() {
            NotFoundException("Application with $id not found")
        }
        return app.answers;
    }

   fun getOneAnswer(id:Long, name: String) = AnswerDTO(0, "experience","3","Int") //TODO("Add to answer service????")

    fun addAnswer(id:Long, answer:AnswerDAO) {
        val app = applications.findById(id).orElseThrow() {
            NotFoundException("Application with $id not found")
        }
        answer.id = 0;
        answers.save(answer)

        app.answers.add(answer)
        applications.save(app)
    }

    fun editAnswer(id:Long, answer:AnswerDAO) {
        TODO("Not yet implemented")
    }

    fun deleteAnswer(id:Long, answer:AnswerDAO) {
        TODO("Not yet implemented")
    }

}