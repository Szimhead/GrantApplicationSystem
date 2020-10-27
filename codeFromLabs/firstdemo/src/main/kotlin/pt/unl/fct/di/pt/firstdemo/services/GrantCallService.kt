package pt.unl.fct.di.pt.firstdemo.services

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.*
import java.util.*

@Service
class GrantCallService(val calls: GrantCallRepository, val apps: ApplicationRepository, val panels: PanelRepository, val reviewers: ReviewerRepository,val dataItems: DataItemRepository, val students: StudentRepository) {
    fun getAll(): Iterable<GrantCallDAO> = calls.findAll()

    fun getAllOpen() = calls.findByOpenDateBeforeAndCloseDateAfter(Date(), Date())

    fun getOne(id: Long): GrantCallDAO = calls.findById(id).orElseThrow {
        NotFoundException("Grant Call with id $id not found")
    }

    @Transactional
    fun addCall(call: GrantCallDAO) {
        var panel = PanelDAO()
        panel.grantCall = call

        call.panel = panel

        calls.save(call)
    }

    @Transactional
    fun editCall(id: Long, call: GrantCallDAO) {
        val editedCall = calls.findById(id).orElseThrow {
            NotFoundException("Grant Call with id $id not found")
        }
        editedCall.title = call.title
        editedCall.description = call.description
        editedCall.funding = call.funding
        editedCall.openDate = call.openDate
        editedCall.closeDate = call.closeDate
        calls.save(editedCall)
    }

    @Transactional
    fun deleteCall(id: Long) {
        val deletedCall = calls.findById(id).orElseThrow {
            NotFoundException("Grant Call with id $id not found")
        }
        calls.delete(deletedCall)
    }

    /* Application handling */
    @Transactional
    fun getCallApplications(id: Long): Set<ApplicationDAO> {
        val call = calls.findById(id).orElseThrow {
            NotFoundException("Grant Call with id $id not found")
        }
        return call.applications
    }

    @Transactional
    fun addApplication(id: Long, app: ApplicationDAO, studentId: Long) {
        val call = calls.findById(id).orElseThrow {
            NotFoundException("Application with title $id not found")
        }
        val student = students.findById(studentId).orElseThrow {
            NotFoundException("Student with id $studentId not found")
        }
        app.grantCall = call
        student.applications.add(app)
        apps.save(app)
    }

    /* Panel handling */
    @Transactional
    fun getPanelFromGrantCall(id: Long): PanelDAO {
        val call = calls.findById(id).orElseThrow {
            NotFoundException("Application with title $id not found")
        }
        val panel = call.panel

        if(panel == null)
            throw NotFoundException("Application does not have a panel")
        else return panel
    }

    @Transactional
    fun getReviewers(id: Long): Set<ReviewerDAO> {
        val call = calls.findById(id).orElseThrow {
            NotFoundException("Application with title $id not found")
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
    fun addReviewerToPanel(id: Long, reviewer: ReviewerDAO){
        val call = calls.findById(id).orElseThrow {
            NotFoundException("Grant Call with id $id not found")
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
    fun deleteReviewerFromPanel(id: Long, reviewerId:Long) {
        val reviewer = reviewers.findById(reviewerId).orElseThrow {
            NotFoundException("Reviewer with $reviewerId not found")
        }
        val call = calls.findById(id).orElseThrow {
            NotFoundException("Grant Call with id $id not found")
        }
        if (reviewer != null) {
            reviewer.panels.remove(call.panel)
            reviewers.save(reviewer)
        }
    }

    /* Data item handling */
    @Transactional
    fun getAllDataItems(id: Long): Set<DataItemDAO> {
        val call = calls.findById(id).orElseThrow {
            NotFoundException("Grant Call with id $id not found")
        }
        return call.dataItems
    }

    @Transactional
    fun getOneDataItem(id: Long, dataItemId: Long): DataItemDAO {
        calls.findById(id).orElseThrow {
            NotFoundException("Application with title $id not found")
        }
        return dataItems.findById(dataItemId).orElseThrow {
            NotFoundException("Data Item with id $dataItemId not found")
        }
    }

    @Transactional
    fun addDataItem(id: Long, dataItem: DataItemDAO) {
        val call = calls.findById(id).orElseThrow {
            NotFoundException("Grant Call with id $id not found")
        }
        dataItem.grantCalls.add(call)
        dataItems.save(dataItem)
    }

    @Transactional
    fun deleteDataItem(id: Long, name: String) {
        val call = calls.findById(id).orElseThrow {
            NotFoundException("Grant Call with id $id not found")
        }
        val deletedDataItem = dataItems.findByNameAndGrantCalls(name, call).orElseThrow {
            NotFoundException("Data item with $name not found")
        }
        dataItems.delete(deletedDataItem)
    }

    @Transactional
    fun editDataItem(id: Long, name: String, dataItem: DataItemDAO) {
        val call = calls.findById(id).orElseThrow {
            NotFoundException("Grant Call with id $id not found")
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