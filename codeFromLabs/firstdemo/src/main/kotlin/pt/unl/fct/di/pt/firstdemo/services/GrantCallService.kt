package pt.unl.fct.di.pt.firstdemo.services

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.*
import java.util.*

@Service
class GrantCallService(val calls: GrantCallRepository, val apps: ApplicationRepository, val panels: PanelRepository, val reviewers: ReviewerRepository,val dataItems: DataItemRepository) {
    fun getAll(): Iterable<GrantCallDAO> = calls.findAll()

    fun getAllOpen() = calls.findByOpenDateBeforeAndCloseDateAfter(Date(), Date())

    fun getOne(title: String): GrantCallDAO = calls.findByTitle(title).orElseThrow {
        NotFoundException("Application with title $title not found")
    }

    fun addCall(call: GrantCallDAO) {
        calls.save(call)
    }

    @Transactional
    fun editCall(title: String, call: GrantCallDAO) {
        val editedCall = calls.findByTitle(title).orElseThrow {
            NotFoundException("Application with title $title not found")
        }
        editedCall.title = call.title
        editedCall.description = call.description
        editedCall.funding = call.funding
        editedCall.openDate = call.openDate
        editedCall.closeDate = call.closeDate
        calls.save(editedCall)
    }

    @Transactional
    fun deleteCall(title: String) {
        val deletedCall = calls.findByTitle(title).orElseThrow {
            NotFoundException("Application with title $title not found")
        }
        calls.delete(deletedCall)
    }

    /* Application handling */
    @Transactional
    fun getCallApplications(title: String): Set<ApplicationDAO> {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Application with title $title not found")
        }
        return call.applications
    }

    @Transactional
    fun addApplication(title: String, app: ApplicationDAO) {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Application with title $title not found")
        }
        app.grantCall = call;
        apps.save(app)

        call.applications.add(app)
        calls.save(call)


    }

    /* Panel handling */
    @Transactional
    fun getPanelFromGrantCall(title: String): PanelDAO {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Application with title $title not found")
        }
        val panel = call.panel

        if(panel == null)
            throw NotFoundException("Application does not have a panel")
        else return panel;
    }

    @Transactional
    fun addPanel(title: String, panel: PanelDAO) {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Application with title $title not found")
        }                                                                   // need orElseThrow() and should we make an id for calls?
        panel.grantCall = call                                              //also maybe we should just create a call with an empty panel so we just need to add reviewers
        panels.save(panel)
    }

    @Transactional
    fun getReviewers(title: String): List<ReviewerDAO> {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Application with title $title not found")
        }
        val panel = call.panel
        if(panel == null) {
            throw NotFoundException("Application does not have a panel")
        }
        else {
            return panel.reviewers
        }
    }

    @Transactional
    fun addReviewerToPanel(title: String, reviewer: ReviewerDAO){
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Application with title $title not found")
        }
        val panel = call.panel
        if(panel == null) {
            throw NotFoundException("Application does not have a panel")
        }
        else {
            reviewer.panels.add(panel)
            reviewers.save(reviewer)
        }
    }

    @Transactional
    fun deleteReviewerFromPanel(title: String, reviewerId:Long) {
        val reviewer = reviewers.findById(reviewerId).orElse(null)
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Application with title $title not found")
        }
        if (reviewer != null) {
            reviewer.panels.remove(call.panel)
            reviewers.save(reviewer)
        }
    }

    /* Data item handling */
    @Transactional
    fun getAllDataItems(title: String): Set<DataItemDAO> {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Application with title $title not found")
        }
        return call.dataItems
    }

    @Transactional
    fun getOneDataItem(title: String, name: String): DataItemDAO {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Application with title $title not found")
        }
        return dataItems.findByNameAndGrantCalls(name, call)  // i added an id because i also needed to do what you did here, keep id!
    }

    @Transactional
    fun addDataItem(title: String, dataItem: DataItemDAO) {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Application with title $title not found")
        }
        dataItem.grantCalls.add(call)
        dataItems.save(dataItem)
    }

    @Transactional
    fun deleteDataItem(title: String, name: String) {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Application with title $title not found")
        }
        val deletedDataItem = dataItems.findByNameAndGrantCalls(name, call)
        dataItems.delete(deletedDataItem)
    }

    @Transactional
    fun editDataItem(title: String, name: String, dataItem: DataItemDAO) {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Application with title $title not found")
        }
        val editedDataItem = dataItems.findByNameAndGrantCalls(name, call)
        editedDataItem.name = dataItem.name
        editedDataItem.dataType = dataItem.dataType
        editedDataItem.isMandatory = dataItem.isMandatory
        dataItems.save(editedDataItem)
    }


}