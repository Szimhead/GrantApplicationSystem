package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.ApplicationDTO
import pt.unl.fct.di.pt.firstdemo.api.GrantCallDTO
import pt.unl.fct.di.pt.firstdemo.api.PanelDTO
import pt.unl.fct.di.pt.firstdemo.api.ReviewerDTO
import java.util.*

@Service
class GrantCallService {
    fun getAll() = listOf<GrantCallDTO>(GrantCallDTO("all success"))

    fun getAllOpen() = listOf<GrantCallDTO>(GrantCallDTO("all open success"))

    fun getOne(title: String) = GrantCallDTO("one with title: $title")

    fun addCall() {
        TODO("Not yet implemented")
    }

    fun editCall(title: String) {
        TODO("Not yet implemented")
    }

    fun deleteCall(title: String) {
        TODO("Not yet implemented")
    }

    /* Panel handling */
    fun getPanel() = PanelDTO(
            0,
            "grant call title",
            listOf<ReviewerDTO>(ReviewerDTO(0, "John"))
    )

    fun addPanel(panelId: Long) {
        TODO("Not yet implemented")
    }

    /* Application handling */
    fun getCallApplications(title: String) = listOf<ApplicationDTO>(
            ApplicationDTO(1, Date(), 0),
            ApplicationDTO(2, Date(), 1)
    )

    fun getOneCallApplication(title: String, id: Long) = ApplicationDTO(id, Date(), 0)

    fun addApplication(title: String, id: Long) {
        TODO("Not yet implemented")
    }

}