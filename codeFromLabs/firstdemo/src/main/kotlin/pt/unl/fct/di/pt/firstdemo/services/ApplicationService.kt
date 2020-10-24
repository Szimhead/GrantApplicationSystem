package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pt.unl.fct.di.pt.firstdemo.api.ReviewDTO
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.AnswerRepository
import pt.unl.fct.di.pt.firstdemo.model.ApplicationRepository
import pt.unl.fct.di.pt.firstdemo.model.ReviewRepository
import pt.unl.fct.di.pt.firstdemo.model.ReviewerRepository

@Service
class ApplicationService(val applications: ApplicationRepository, val reviews: ReviewRepository, val answers:AnswerRepository, val reviewers:ReviewerRepository) {

    fun getAll(): Iterable<ApplicationDAO> = applications.findAll()

    fun getOne(id:Long): ApplicationDAO = applications.findById(id).orElseThrow {
        NotFoundException("Application with $id not found")
    }

    @Transactional
    fun deleteApplication(id: Long) = applications.deleteById(id)

    @Transactional
    fun editApplication(id:Long, app:ApplicationDAO) {
        val editedApplication = applications.findById(id).orElseThrow {
            NotFoundException("Application with $id not found")
        }
        editedApplication.submissionDate = app.submissionDate
        editedApplication.status = app.status
        editedApplication.grantCall = app.grantCall
        applications.save(editedApplication)
    }

    /* Review handling */
    fun getAllReviewsFromApplication(id: Long): Iterable<ReviewDAO> {
        val app = applications.findById(id).orElseThrow {
            NotFoundException("Application with $id not found")
        }
        return app.reviews
    }

    @Transactional
    fun addReview(id: Long, reviewerId: Long, review:ReviewDAO) {
        val app = applications.findById(id).orElseThrow {
            NotFoundException("Application with $id not found")
        }
        val reviewer = reviewers.findById(reviewerId).orElseThrow {
            NotFoundException("Reviewer with $reviewerId not found")
        }
        review.id = 0
        review.application = app
        review.reviewer = reviewer

        reviews.save(review)
    }

    @Transactional
    fun deleteReview(id:Long, reviewId: Long) {
        val review = reviews.findById(reviewId).orElseThrow {
            NotFoundException("Review with $reviewId not found")
        }

        reviews.delete(review)
    }

    @Transactional
    fun editReview(id:Long, review:ReviewDTO) {
        val editedReview = reviews.findById(review.id).orElseThrow {
            NotFoundException("Review with id not found")
        }

        editedReview.isAccepted
        editedReview.comment
        reviews.save(editedReview)
    }

    /* Answers handling */
    fun getAllAnswers(id:Long) : Iterable<AnswerDAO> {
        val app = applications.findById(id).orElseThrow {
            NotFoundException("Application with $id not found")
        }
        return app.answers
    }

   fun getOneAnswer(id:Long, answerId:Long): AnswerDAO = answers.findById(answerId).orElseThrow {
       NotFoundException("Answer with $answerId not found")
   }

   @Transactional
   fun addAnswer(id:Long, answer:AnswerDAO) {
        val app = applications.findById(id).orElseThrow {
            NotFoundException("Application with $id not found")
        }
        answer.id = 0
        answer.application = app
        answers.save(answer)
    }

    @Transactional
    fun editAnswer(id:Long, answer:AnswerDAO) {
        val editedAnswer = answers.findById(answer.id).orElseThrow {
            NotFoundException("Answer with id not found")
        }

        editedAnswer.name = answer.name
        editedAnswer.value = answer. value
        editedAnswer.dataType = answer. dataType
    }

    @Transactional
    fun deleteAnswer(id:Long, answerId:Long) {
        val answer = answers.findById(answerId).orElseThrow {
            NotFoundException("Answer with $answerId not found")
        }

        answers.delete(answer)
    }

}