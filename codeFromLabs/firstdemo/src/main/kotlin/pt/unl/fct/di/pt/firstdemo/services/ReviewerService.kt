package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.InstitutionDTO
import pt.unl.fct.di.pt.firstdemo.api.PanelDTO
import pt.unl.fct.di.pt.firstdemo.api.ReviewDTO
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
    fun getPanels(reviewerNr: Long) = listOf<PanelDTO>(PanelDTO(
            0,
            "Grant title all",
            listOf<ReviewerDTO>(ReviewerDTO(1, "Hannah"))
    ))

    fun getOnePanel(reviewerNr: Long, p_id:Long) = PanelDTO(
            1,
            "Grant title one",
            listOf<ReviewerDTO>(ReviewerDTO(2, "Mary"))
    )

    /* reviews handling */
    fun getReviews(reviewerNr: Long) = listOf<ReviewDTO>(ReviewDTO(1, true, "very nice"))

    fun getOneReview(reviewerNr: Long, r_id:Long) = ReviewDTO(2, false, "not very nice")
}