package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.PanelDTO
import pt.unl.fct.di.pt.firstdemo.api.ReviewDTO
import pt.unl.fct.di.pt.firstdemo.api.UserDTO

@Service
class ReviewerService {
    fun getAll() = listOf<UserDTO>(UserDTO(1, "John Smith", "john.s@gmail.com", "no address"))

    fun getOne(id:Long) = UserDTO(1, "John Smith", "john.s@gmail.com", "no address")

    fun addReviewer(reviewerNr: Long) {
        TODO("Not yet implemented")
    }

    fun deleteReviewer(reviewerNr:Long) {
        TODO("Not yet implemented")
    }

    fun updateReviewer(reviewerNr: Long) {
        TODO("Not yet implemented")
    }

    /* panel handling */
    fun getPanels(reviewerNr: Long) = listOf<PanelDTO>(PanelDTO(0))

    fun getOnePanel(reviewerNr: Long, p_id:Long) = PanelDTO(1)

    /* reviews handling */
    fun getReviews(reviewerNr: Long) = listOf<ReviewDTO>(ReviewDTO(1, true, "very nice"))

    fun getOneReview(reviewerNr: Long, r_id:Long) = ReviewDTO(2, false, "not very nice")

}