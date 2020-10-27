package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.*

@RestController
class GrantCallController(val calls:GrantCallService): GrantCallAPI {

    override fun getAll() = calls.getAll().map { GrantCallDTO(it) }

    override fun getAllOpen() = calls.getAllOpen().map { GrantCallDTO(it) }

    override fun getOne(id: Long) = GrantCallDTO(calls.getOne(id))

    override fun addCall(call: GrantCallDTO) = calls.addCall(GrantCallDAO(call))

    override fun editCall(id: Long, call: GrantCallDTO) = calls.editCall(id, GrantCallDAO(call))

    override fun deleteCall(id: Long) = calls.deleteCall(id)

    override fun getAllApplicationsFromGrantCall(id: Long) = calls.getCallApplications(id).map { ApplicationDTO(it) }

    override fun addApplication(id: Long, app: ApplicationDTO) = calls.addApplication(id, ApplicationDAO(app), app.studentId)

    override fun getPanelFromGrantCall(id: Long) = PanelDTO(calls.getPanelFromGrantCall(id))

    override fun addPanel(id: Long, panel: PanelDTO) = calls.addPanel(id, PanelDAO(panel))

    override fun getReviewers(id: Long) = calls.getReviewers(id).map { UserDTO(it) }

    override fun addReviewerToPanel(id: Long, reviewer: UserDTO) = calls.addReviewerToPanel(id, ReviewerDAO(reviewer))

    override fun deleteReviewerFromPanel(id: Long, reviewerId:Long) = calls.deleteReviewerFromPanel(id, reviewerId)

    override fun getAllDataItems(id: Long) = calls.getAllDataItems(id).map { DataItemDTO(it) }

    override fun getOneDataItem(id: Long, name: String) = DataItemDTO(calls.getOneDataItem(id, name))

    override fun addDataItem(id: Long, dataItem: DataItemDTO) = calls.addDataItem(id, DataItemDAO(dataItem))

    override fun deleteDataItem(id: Long, name: String) = calls.deleteDataItem(id, name)

    override fun editDataItem(id: Long, name: String, dataItem: DataItemDTO) = calls.editDataItem(id, name, DataItemDAO(dataItem))

}