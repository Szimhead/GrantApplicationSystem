package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.api.PanelDTO
import pt.unl.fct.di.pt.firstdemo.api.ReviewerDTO

@Service
class PanelService {

    fun getAll() = listOf<PanelDTO>(PanelDTO(4, "Best code",
            listOf<ReviewerDTO>(ReviewerDTO(1, "John Smith"),
            ReviewerDTO(2, "Donald Trump"))))

    fun getOne(id:Long) = PanelDTO(4, "Best code",
            listOf<ReviewerDTO>(ReviewerDTO(1, "John Smith"),
                    ReviewerDTO(2, "Donald Duck")))

    fun addPanel() = print("add panel działa")

    fun editPanel(id:Long) = print ("edit panel działa")

    fun deletePanel(id:Long) = print ("delete panel działa")

    fun getReviewers(id: Long) = listOf<ReviewerDTO>(ReviewerDTO(0, "Louis"))

    fun deleteReviewerFromPanel(panelId: Long, reviewerId:Long) = print ("delete reviewer from panel działa")

    fun addReviewerToPanel(id:Long, reviewerId: Long) = print("add reviewer $reviewerId to panel działa")
}