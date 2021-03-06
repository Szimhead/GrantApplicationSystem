package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pt.unl.fct.di.pt.firstdemo.api.ReviewDTO
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.*

@Service
class ApplicationService(val applications: ApplicationRepository, val reviews: ReviewRepository, val answers:AnswerRepository, val reviewers:ReviewerRepository, val studs:StudentRepository, val calls:GrantCallRepository) {

    fun getAll(): Iterable<ApplicationDAO> = applications.findAll()

    fun getOne(id:Long): ApplicationDAO = applications.findById(id).orElseThrow {
        NotFoundException("Application with $id not found")
    }

    @Transactional
    fun deleteApplication(app: ApplicationDAO) {
        val call = app.grantCall
        val student = app.student
        call.applications.remove(app)
        student.applications.remove(app)

        calls.save(call)
        studs.save(student)
        applications.delete(app)
    }

    @Transactional
    fun editApplication(editedApp: ApplicationDAO, newApp:ApplicationDAO) {
        editedApp.submissionDate = newApp.submissionDate
        editedApp.status = newApp.status
        editedApp.grantCall = newApp.grantCall
        applications.save(editedApp)
    }

    /* Review handling */
    fun getAllReviewsFromApplication(app: ApplicationDAO): Iterable<ReviewDAO> {
        return app.reviews
    }

    @Transactional
    fun getOneReview(app: ApplicationDAO, reviewId: Long) : ReviewDAO {
        val review = reviews.findById(reviewId).orElseThrow {
            NotFoundException("Review with $reviewId not found")
        }

        if(review.application == app)
            return review
        else
            throw NotFoundException("Review with $reviewId not found")
    }

    @Transactional
    fun addReview(app: ApplicationDAO, review:ReviewDAO) {
        app.reviews.add(review)
        reviews.save(review)
    }

    @Transactional
    fun deleteReview(app: ApplicationDAO, review:ReviewDAO) {
        if(review.application == app) {
            app.reviews.remove(review)
            reviews.delete(review)
        }

    }

    @Transactional
    fun editReview(app: ApplicationDAO, editedReview: ReviewDAO, newReview:ReviewDAO) {
        if(editedReview.application == app) {
            editedReview.isAccepted = newReview.isAccepted
            editedReview.comment = newReview.comment
            reviews.save(editedReview)
        }
    }

    /* Answers handling */
    fun getAllAnswers(app: ApplicationDAO) : Iterable<AnswerDAO> {
        return app.answers
    }

   fun getOneAnswer(app: ApplicationDAO, answerId:Long): AnswerDAO {
       val answer = answers.findById(answerId).orElseThrow {
           NotFoundException("Answer with $answerId not found")
       }

       if(answer.application == app)
           return answer
       else
           throw NotFoundException("Answer with $answerId not found")
   }

   @Transactional
   fun addAnswer(app: ApplicationDAO, answer:AnswerDAO) {
        app.answers.add(answer)
        answers.save(answer)
    }

    @Transactional
    fun editAnswer(app: ApplicationDAO, editedAnswer: AnswerDAO, newAnswer:AnswerDAO) {
        if(editedAnswer.application == app) {
            editedAnswer.name = newAnswer.name
            editedAnswer.value = newAnswer.value
            editedAnswer.dataType = newAnswer.dataType
            answers.save(editedAnswer)
        }
    }

    @Transactional
    fun deleteAnswer(app: ApplicationDAO, answer:AnswerDAO) {
        if (answer.application == app) {
            app.answers.remove(answer)
            answers.delete(answer)
        }
    }

}