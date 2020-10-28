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
            NotFoundException("Grant Call with id $id not found")
        }
        val student = students.findById(studentId).orElseThrow {
            NotFoundException("Student with id $studentId not found")
        }

        student.applications.add(app)
        call.applications.add(app)

        apps.save(app)
    }

    @Transactional
    fun deleteApplication(id: Long, appId: Long) {
        val call = calls.findById(id).orElseThrow {
            NotFoundException("Grant Call with id $id not found")
        }
        var app = apps.findById(appId).orElseThrow {
            NotFoundException("Application with id $appId not found")
        }
        var student = app.student
        if(app.grantCall.id == call.id) {
            student.applications.remove(app)
            call.applications.remove(app)
            apps.deleteById(appId)
        }
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
    fun addReviewerToPanel(id: Long, reviewerId: Long){
        val call = calls.findById(id).orElseThrow {
            NotFoundException("Grant Call with id $id not found")
        }
        val reviewer = reviewers.findById(reviewerId).orElseThrow {
            NotFoundException("Reviewer with id $id not found")
        }

        val panel = call.panel
        if(panel == null) {
            throw NotFoundException("Application does not have a panel")
        }
        else {
            reviewer.panels.add(panel)
            panel.reviewers.add(reviewer)
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
        val panel = call.panel
        if(panel == null) {
            throw NotFoundException("Application does not have a panel")
        }

        panel.reviewers.remove(reviewer)
        reviewer.panels.remove(panel)

        reviewers.save(reviewer)
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
        call.dataItems.add(dataItem)
        dataItems.save(dataItem)
    }

    @Transactional
    fun deleteDataItem(id: Long, dataItemId: Long) {
        val call = calls.findById(id).orElseThrow {
            NotFoundException("Grant Call with id $id not found")
        }
        val deletedDataItem = dataItems.findById(dataItemId).orElseThrow {
            NotFoundException("Data item with id $dataItemId not found")
        }

        call.dataItems.remove(deletedDataItem)
        dataItems.delete(deletedDataItem)
    }

    @Transactional
    fun editDataItem(id: Long, dataItemId: Long, dataItem: DataItemDAO) {
        val call = calls.findById(id).orElseThrow {
            NotFoundException("Grant Call with id $id not found")
        }
        val editedDataItem = dataItems.findById(dataItemId).orElseThrow {
            NotFoundException("Data item with id $dataItemId not found")
        }
        editedDataItem.name = dataItem.name
        editedDataItem.dataType = dataItem.dataType
        editedDataItem.isMandatory = dataItem.isMandatory
        dataItems.save(editedDataItem)
    }


}