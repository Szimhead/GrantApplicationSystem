package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.*
import pt.unl.fct.di.pt.firstdemo.model.GrantCallRepository
import java.util.*

@Service
class GrantCallService(val calls: GrantCallRepository) {
    fun getAll() = calls.findAll()

    fun getAllOpen() = calls.findByOpenDateBeforeAndCloseDateAfter(Date(), Date())

    fun getOne(title: String) = calls.findByTitle(title)

    fun addCall(call: GrantCallDAO) = calls.save(call)

    fun editCall(title: String) {
        TODO("Not yet implemented")
    }

    fun deleteCall(title: String) {
        TODO("Not yet implemented")
    }

    /* Application handling */
    fun getCallApplications(title: String) = listOf<ApplicationDTO>(
            ApplicationDTO(1, Date(), 0),
            ApplicationDTO(2, Date(), 1)
    )

    fun addApplication(title: String, app: ApplicationDTO) {
        TODO("Not yet implemented")
    }

    /* Panel handling */
    fun getPanelFromGrantCall(title: String) = PanelDTO(0)

    fun addPanel(title: String, panel: PanelDTO) {
        TODO("Not yet implemented")
    }

    fun getReviewers(title: String) = listOf<UserDTO>(UserDTO(1, "John Smith", "john.s@gmail.com", "no address"))

    fun addReviewerToPanel(title: String, reviewer: UserDTO){
        TODO("Not yet implemented")
    }

    fun deleteReviewerFromPanel(title: String, reviewerId:Long) {
        TODO("Not yet implemented")
    }

    /* Data item handling */
    fun getAllDataItems(title: String) = listOf<DataItemDTO>()

    fun getOneDataItem(title: String, name: String) = DataItemDTO("age", "Int", true)

    fun addDataItem(title: String, dataItem: DataItemDTO) {
        TODO("Not yet implemented")
    }

    fun deleteDataItem(title: String, name: String) {
        TODO("Not yet implemented")
    }

    fun editDataItem(title: String, name: String) {
        TODO("Not yet implemented")
    }


}