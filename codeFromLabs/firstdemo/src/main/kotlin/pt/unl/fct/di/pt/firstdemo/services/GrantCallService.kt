package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pt.unl.fct.di.pt.firstdemo.api.*
import pt.unl.fct.di.pt.firstdemo.model.*
import java.util.*

@Service
class GrantCallService(val calls: GrantCallRepository, val apps: ApplicationRepository, val panels: PanelRepository, val reviewers: ReviewerRepository,val dataItems: DataItemRepository) {
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

    @Transactional
    fun addPanel(title: String, panel: PanelDAO) {
        val call = calls.findByTitle(title)
        panel.grantCall = call
        panels.save(panel)
    }

    @Transactional
    fun getReviewers(title: String): List<ReviewerDAO> {
        val call = calls.findByTitle(title)
        return call.panel.reviewers
    }

    @Transactional
    fun addReviewerToPanel(title: String, reviewer: ReviewerDAO){
        val call = calls.findByTitle(title)
        val panel = call.panel
        reviewer.panels.add(panel)
        reviewers.save(reviewer)
    }

    @Transactional
    fun deleteReviewerFromPanel(title: String, reviewerId:Long) {
        val reviewer = reviewers.findById(reviewerId).orElse(null)
        val call = calls.findByTitle(title)
        if (reviewer != null) {
            reviewer.panels.remove(call.panel)
            reviewers.save(reviewer)
        }
    }

    /* Data item handling */
    @Transactional
    fun getAllDataItems(title: String): List<DataItemDAO> {
        val call = calls.findByTitle(title)
        return call.dataItems
    }

    @Transactional
    fun getOneDataItem(title: String, name: String): DataItemDAO {
        val call = calls.findByTitle(title)
        return dataItems.findByNameAndGrantCall(name, call)
    }

    @Transactional
    fun addDataItem(title: String, dataItem: DataItemDAO) {
        val call = calls.findByTitle(title)
        dataItem.grantCall = call
        dataItems.save(dataItem)
    }

    @Transactional
    fun deleteDataItem(title: String, name: String) {
        val call = calls.findByTitle(title)
        val deletedDataItem = dataItems.findByNameAndGrantCall(name, call)
        dataItems.delete(deletedDataItem)
    }

    @Transactional
    fun editDataItem(title: String, name: String, dataItem: DataItemDAO) {
        val call = calls.findByTitle(title)
        val editedDataItem = dataItems.findByNameAndGrantCall(name, call)
        editedDataItem.name = dataItem.name
        editedDataItem.dataType = dataItem.dataType
        editedDataItem.isMandatory = dataItem.isMandatory
        dataItems.save(editedDataItem)
    }


}