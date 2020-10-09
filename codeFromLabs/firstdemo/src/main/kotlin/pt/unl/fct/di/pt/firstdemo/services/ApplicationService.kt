package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.AnswerDTO
import pt.unl.fct.di.pt.firstdemo.api.ApplicationDTO
import pt.unl.fct.di.pt.firstdemo.api.ReviewDTO
import java.util.*

@Service
class ApplicationService {

    fun getAll() = listOf<ApplicationDTO>(ApplicationDTO(1, Date(), 0))

    fun getOne(id:Long) = ApplicationDTO(2, Date(), 0)

    fun deleteApplication(id: Long) {
        TODO("Not yet implemented")
    }

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