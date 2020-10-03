package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.InstitutionDTO
import pt.unl.fct.di.pt.firstdemo.api.PanelDTO
import pt.unl.fct.di.pt.firstdemo.api.ReviewerDTO

@Service
class ReviewerService {
    fun getAll() = listOf<ReviewerDTO>(ReviewerDTO(1, "John Smith"))

    fun getOne(id:Long) = ReviewerDTO(2, "Mike Smith")

    fun deleteReviewer(reviewerNr:Long) {
        TODO("Not yet implemented")
    }

    fun updateReviewer(reviewerNr: Long) {
        TODO("Not yet implemented")
    }


    /* panel handling */
    fun getPanels(reviewerNr: Long) = listOf<PanelDTO>(PanelDTO(...))

    fun getOnePanel(reviewerNr: Long, p_id:Long) = PanelDTO(...)


    /* reviews handling */
    fun getReviews(reviewerNr: Long) = listOf<ReviewDTO>(ReviewDTO(3))

    fun getOneReview(reviewerNr: Long, r_id:Long) = ReviewDTO(4)
}