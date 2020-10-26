package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pt.unl.fct.di.pt.firstdemo.api.*
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.*
import java.util.*

@Service
class GrantCallService(val calls: GrantCallRepository, val apps: ApplicationRepository, val panels: PanelRepository, val reviewers: ReviewerRepository,val dataItems: DataItemRepository) {
    fun getAll() = calls.findAll()

    fun getAllOpen() = calls.findByOpenDateBeforeAndCloseDateAfter(Date(), Date())

    fun getOne(title: String) = calls.findByTitle(title)

    @Transactional
    fun addCall(call: GrantCallDAO) {
        val panel = PanelDAO(0, ReviewerDAO(), mutableListOf(),call)
        call.id = 0
        panel.grantCall = call
        panels.save(panel)
        calls.save(call)
    }

    @Transactional
    fun editCall(title: String, call: GrantCallDAO) {
        val editedCall = calls.findByTitle(title).orElseThrow {
            NotFoundException("Grant Call with $title not found")
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
            NotFoundException("Grant Call with $title not found")
        }
        calls.delete(deletedCall)
    }

    /* Application handling */
    @Transactional
    fun getCallApplications(title: String): List<ApplicationDAO> {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Grant Call with $title not found")
        }
        return  call.applications
    }

    @Transactional
    fun addApplication(title: String, app: ApplicationDAO) {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Grant Call with $title not found")
        }
        app.id = 0
        app.grantCall = call
        apps.save(app)
    }

    /* Panel handling */
    @Transactional
    fun getPanelFromGrantCall(title: String): PanelDAO {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Grant Call with $title not found")
        }
        return call.panel
    }


    @Transactional
    fun getReviewers(title: String): List<ReviewerDAO> {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Grant Call with $title not found")
        }
        return call.panel.reviewers
    }

    @Transactional
    fun addReviewerToPanel(title: String, reviewer: ReviewerDAO){
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Grant Call with $title not found")
        }
        val panel = call.panel
        reviewer.panels.add(panel)
        reviewers.save(reviewer)
    }

    @Transactional
    fun deleteReviewerFromPanel(title: String, reviewerId:Long) {
        val reviewer = reviewers.findById(reviewerId).orElseThrow {
            NotFoundException("Reviewer with $reviewerId not found")
        }
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Grant Call with $title not found")
        }
        if (reviewer != null) {
            reviewer.panels.remove(call.panel)
            reviewers.save(reviewer)
        }
    }

    /* Data item handling */
    @Transactional
    fun getAllDataItems(title: String): List<DataItemDAO> {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Grant Call with $title not found")
        }
        return call.dataItems
    }

    @Transactional
    fun getOneDataItem(title: String, name: String): DataItemDAO {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Grant Call with $title not found")
        }
        return dataItems.findByNameAndGrantCalls(name, call).orElseThrow {
            NotFoundException("Data item with $name not found")
        }  // i added an id because i also needed to do what you did here, keep id!
    }

    @Transactional
    fun addDataItem(title: String, dataItem: DataItemDAO) {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Grant Call with $title not found")
        }
        dataItem.grantCalls.add(call)
        dataItems.save(dataItem)
    }

    @Transactional
    fun deleteDataItem(title: String, name: String) {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Grant Call with $title not found")
        }
        val deletedDataItem = dataItems.findByNameAndGrantCalls(name, call).orElseThrow {
            NotFoundException("Data item with $name not found")
        }
        dataItems.delete(deletedDataItem)
    }

    @Transactional
    fun editDataItem(title: String, name: String, dataItem: DataItemDAO) {
        val call = calls.findByTitle(title).orElseThrow {
            NotFoundException("Grant Call with $title not found")
        }
        val editedDataItem = dataItems.findByNameAndGrantCalls(name, call).orElseThrow {
            NotFoundException("Data item with $name not found")
        }
        editedDataItem.name = dataItem.name
        editedDataItem.dataType = dataItem.dataType
        editedDataItem.isMandatory = dataItem.isMandatory
        dataItems.save(editedDataItem)
    }


}