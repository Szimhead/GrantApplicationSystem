package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.ApplicationDTO
import pt.unl.fct.di.pt.firstdemo.api.FinalDTO
import pt.unl.fct.di.pt.firstdemo.api.ReviewDTO
import java.util.*

@Service
class ApplicationService {

    fun getAll() = listOf<ApplicationDTO>(ApplicationDTO(1, Date(), 0))

    fun getOne(id:Long) = ApplicationDTO(2, Date(), 0)

    /* Review handling */
    fun getAllReviews() = listOf<ReviewDTO>(ReviewDTO(3))

    fun getReview(appId: Long, reviewId: Long) = ReviewDTO(4)

    fun addReview(appId: Long, reviewId: Long) {
        TODO("Not yet implemented")
    }

    /* Final Evaluation handling */
    fun getFinal() = FinalDTO(5, "passed", "Very good student!")

}