package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.AnswerDTO
import pt.unl.fct.di.pt.firstdemo.api.ApplicationDTO
import pt.unl.fct.di.pt.firstdemo.api.ReviewDTO
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.ApplicationRepository
import java.util.*

@Service
class ApplicationService(val applications: ApplicationRepository) {

    fun getAll(): Iterable<ApplicationDAO> = applications.findAll()

    fun getOne(id:Long): ApplicationDAO = applications.findById(id).orElseThrow {
        NotFoundException("Application with $id not found")
    }

    fun deleteApplication(id: Long) = applications.deleteById(id)

    /*fun addOne(application: ApplicationDAO) {
        application.id = 0;
        applications.save(application)
    } */

    fun editApplication(id:Long) {
        TODO("Not yet implemented")
    }

    /* Review handling */
    fun getAllReviewsFromApplication(id: Long) = listOf<ReviewDTO>(ReviewDTO(5, true, "ok"))

    fun getOneReview(id: Long, review_id: Long) = ReviewDTO(4, true, "I accept")

    fun addReview(id: Long, review_id: Long) {
        TODO("Not yet implemented")
    }

    fun deleteReview(id:Long, review_id: Long) {
        TODO("Not yet implemented")
    }

    fun editReview(id:Long, review_id: Long) {
        TODO("Not yet implemented")
    }

    /* Answers handling */
    fun getAllAnswers(id:Long) = listOf<AnswerDTO>(AnswerDTO("experience","3","Int"))

    fun getOneAnswer(id:Long, name: String) = AnswerDTO("experience","3","Int")

    fun addAnswer(id:Long, name: String) {
        TODO("Not yet implemented")
    }

    fun editAnswer(id:Long, name: String) {
        TODO("Not yet implemented")
    }

    fun deleteAnswer(id:Long, name: String) {
        TODO("Not yet implemented")
    }

}