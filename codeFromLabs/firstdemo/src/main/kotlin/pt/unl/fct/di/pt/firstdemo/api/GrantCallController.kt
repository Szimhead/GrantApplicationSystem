package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.GrantCallService

@RestController
class GrantCallController(val calls:GrantCallService): GrantCallAPI {

    override fun getAll() = calls.getAll()

    override fun getAllOpen() = calls.getAllOpen()

    override fun getOne(title: String) = calls.getOne(title)

    override fun addCall(title: String) = calls.addCall(title)

    override fun editCall(title: String) = calls.editCall(title)

    override fun deleteCall(title: String) = calls.deleteCall(title)

    override fun getAllApplicationsFromGrantCall(title: String) = calls.getCallApplications(title)

    override fun addApplication(title: String, id: Long) = calls.addApplication(title, id)

    override fun getPanelFromGrantCall(title: String): PanelDTO = calls.getPanelFromGrantCall(title)

    override fun addPanel(title: String) = calls.addPanel(title)

    override fun getReviewers(title: String) = calls.getReviewers(title)

    override fun addReviewerToPanel(title: String, reviewerId: Long) = calls.addReviewerToPanel(title, reviewerId)

    override fun deleteReviewerFromPanel(title: String, reviewerId:Long) = calls.deleteReviewerFromPanel(title, reviewerId)

    override fun getAllDataItems(title: String) = calls.getAllDataItems(title)

    override fun getOneDataItem(title: String, name: String) = calls.getOneDataItem(title, name)

    override fun addDataItem(title: String, name: String) = calls.addDataItem(title, name)

    override fun deleteDataItem(title: String, name: String) = calls.deleteDataItem(title, name)

    override fun editDataItem(title: String, name: String) = calls.editDataItem(title, name)

}