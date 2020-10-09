package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.*
import java.util.*

@Service
class GrantCallService {
    fun getAll() = listOf<GrantCallDTO>(GrantCallDTO("all success", "some grant call", 500000.00,
            Date(),  Date()))

    fun getAllOpen() = listOf<GrantCallDTO>(GrantCallDTO("all success", "some grant call", 500000.00,
            Date(),  Date(2020,10,25)))

    fun getOne(title: String) = GrantCallDTO("all success", "some grant call", 500000.00,
            Date(),  Date())

    fun addCall() {
        TODO("Not yet implemented")
    }

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

    fun addApplication(title: String, id: Long) {
        TODO("Not yet implemented")
    }

    /* Panel handling */
    fun getPanelFromGrantCall(title: String) = PanelDTO(0)

    fun addPanel(title: String) {
        TODO("Not yet implemented")
    }

    fun getReviewers(title: String) = listOf<UserDTO>(UserDTO(1, "John Smith", "john.s@gmail.com", "no address"))

    fun addReviewerToPanel(title: String, reviewerId: Long){
        TODO("Not yet implemented")
    }

    fun deleteReviewerFromPanel(title: String, reviewerId:Long) {
        TODO("Not yet implemented")
    }

    /* Data item handling */
    fun getAllDataItems(title: String) = listOf<DataItemDTO>()

    fun getOneDataItem(title: String, name: String) = DataItemDTO("age", "Int", true)

    fun addDataItem(title: String) {
        TODO("Not yet implemented")
    }

    fun deleteDataItem(title: String, name: String) {
        TODO("Not yet implemented")
    }

    fun editDataItem(title: String, name: String) {
        TODO("Not yet implemented")
    }


}