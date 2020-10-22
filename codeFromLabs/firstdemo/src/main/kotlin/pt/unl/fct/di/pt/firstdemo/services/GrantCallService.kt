package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pt.unl.fct.di.pt.firstdemo.api.*
import pt.unl.fct.di.pt.firstdemo.model.ApplicationRepository
import pt.unl.fct.di.pt.firstdemo.model.GrantCallRepository
import java.util.*

@Service
class GrantCallService(val calls: GrantCallRepository, val apps: ApplicationRepository) {
    fun getAll() = calls.findAll()

    fun getAllOpen() = calls.findByOpenDateBeforeAndCloseDateAfter(Date(), Date())

    fun getOne(title: String) = calls.findByTitle(title)

    fun addCall(call: GrantCallDAO) {
        calls.save(call)
    }

    @Transactional
    fun editCall(title: String, call: GrantCallDAO) {
        val editedCall = calls.findByTitle(title)
        editedCall.title = call.title
        editedCall.description = call.description
        editedCall.funding = call.funding
        editedCall.openDate = call.openDate
        editedCall.closeDate = call.closeDate
        calls.save(editedCall)
    }

    @Transactional
    fun deleteCall(title: String) {
        val deletedCall = calls.findByTitle(title)
        calls.delete(deletedCall)
    }

    /* Application handling */
    @Transactional
    fun getCallApplications(title: String): List<ApplicationDAO> {
        val call = calls.findByTitle(title)
        return  call.applications
    }

    @Transactional
    fun addApplication(title: String, app: ApplicationDAO) {
        val call = calls.findByTitle(title)
        app.grantCall = call
        apps.save(app)
    }

    /* Panel handling */
    @Transactional
    fun getPanelFromGrantCall(title: String): PanelDAO {
        val call = calls.findByTitle(title)
        return call.panel
    }

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

    fun editDataItem(title: String, name: String, dataItem: DataItemDTO) {
        TODO("Not yet implemented")
    }


}