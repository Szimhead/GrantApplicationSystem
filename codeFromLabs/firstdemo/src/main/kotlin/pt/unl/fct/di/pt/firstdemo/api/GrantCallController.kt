package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.*

@RestController
class GrantCallController(val calls:GrantCallService): GrantCallAPI {

    override fun getAll() = calls.getAll().map { GrantCallDTO(it) }

    override fun getAllOpen() = calls.getAllOpen().map { GrantCallDTO(it) }

    override fun getOne(title: String) = GrantCallDTO(calls.getOne(title))

    override fun addCall(call: GrantCallDTO) = calls.addCall(GrantCallDAO(call))

    override fun editCall(title: String, call: GrantCallDTO) = calls.editCall(title, GrantCallDAO(call))

    override fun deleteCall(title: String) = calls.deleteCall(title)

    override fun getAllApplicationsFromGrantCall(title: String) = calls.getCallApplications(title).map { ApplicationDTO(it) }

    override fun addApplication(title: String, app: ApplicationDTO) = calls.addApplication(title, ApplicationDAO(app), app.studentId)

    override fun getPanelFromGrantCall(title: String) = PanelDTO(calls.getPanelFromGrantCall(title))

    override fun addPanel(title: String, panel: PanelDTO) = calls.addPanel(title, PanelDAO(panel))

    override fun getReviewers(title: String) = calls.getReviewers(title).map { UserDTO(it) }

    override fun addReviewerToPanel(title: String, reviewer: UserDTO) = calls.addReviewerToPanel(title, ReviewerDAO(reviewer))

    override fun deleteReviewerFromPanel(title: String, reviewerId:Long) = calls.deleteReviewerFromPanel(title, reviewerId)

    override fun getAllDataItems(title: String) = calls.getAllDataItems(title).map { DataItemDTO(it) }

    override fun getOneDataItem(title: String, name: String) = DataItemDTO(calls.getOneDataItem(title, name))

    override fun addDataItem(title: String, dataItem: DataItemDTO) = calls.addDataItem(title, DataItemDAO(dataItem))

    override fun deleteDataItem(title: String, name: String) = calls.deleteDataItem(title, name)

    override fun editDataItem(title: String, name: String, dataItem: DataItemDTO) = calls.editDataItem(title, name, DataItemDAO(dataItem))

}