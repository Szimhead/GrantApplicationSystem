package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.ReviewDTO

@Service
class ReviewService {
    fun getAll() = listOf(ReviewDTO(1, true, "very nice"))

    fun getOne(id:Long) = ReviewDTO(2, false, "not very nice")

    fun editReview(id:Long) {
        TODO("Not yet implemented")
    }

    fun deleteReview(id:Long) {
        TODO("Not yet implemented")
    }

    fun getAllReviewsFromApplication(id: Long) = listOf(ReviewDTO(3, true, "very very nice"))

    fun addReview(reviewId: Long, appId: Long) {
        TODO("Not yet implemented")
    }
}