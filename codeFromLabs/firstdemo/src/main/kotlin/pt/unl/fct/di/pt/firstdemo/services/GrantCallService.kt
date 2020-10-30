package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.*
import java.util.*

@Service
class GrantCallService(val sponsors: SponsorRepository, val calls: GrantCallRepository, val apps: ApplicationRepository, val panels: PanelRepository, val reviewers: ReviewerRepository,val dataItems: DataItemRepository, val students: StudentRepository) {
    fun getAll(): Iterable<GrantCallDAO> = calls.findAll()

    fun getAllOpen() = calls.findByOpenDateBeforeAndCloseDateAfter(Date(), Date())

    fun getOne(id: Long): GrantCallDAO = calls.findById(id).orElseThrow {
        NotFoundException("Grant Call with id $id not found")
    }

    @Transactional
    fun addCall(call: GrantCallDAO) {
        val panel = PanelDAO(call)
        call.panel = panel

        calls.save(call)
    }

    @Transactional
    fun editCall(editedCall: GrantCallDAO, newCall: GrantCallDAO) {
        editedCall.title = newCall.title
        editedCall.description = newCall.description
        editedCall.funding = newCall.funding
        editedCall.openDate = newCall.openDate
        editedCall.closeDate = newCall.closeDate
        calls.save(editedCall)
    }

    @Transactional
    fun deleteCall(call: GrantCallDAO) {
        call.sponsor.grantCalls.remove(call)
        sponsors.save(call.sponsor)
        calls.delete(call)
    }

    /* Application handling */
    @Transactional
    fun getCallApplications(call: GrantCallDAO): Set<ApplicationDAO> {
        return call.applications
    }

    @Transactional
    fun addApplication(app: ApplicationDAO) {
        //app.student.applications.add(app)
        //app.grantCall.applications.add(app)

        apps.save(app)
    }

    @Transactional
    fun deleteApplication(call: GrantCallDAO, app: ApplicationDAO) {
        call.applications.remove(app)
        app.student.applications.remove(app)
        calls.save(call)
        students.save(app.student)
        apps.delete(app)
    }

    /* Panel handling */
    @Transactional
    fun getPanelFromGrantCall(call: GrantCallDAO): PanelDAO {
        val panel = call.panel

        if(panel == null)
            throw NotFoundException("Grant call does not have a panel")
        else return panel
    }

    @Transactional
    fun setPanelChair(panel: PanelDAO, reviewer: UserDAO.ReviewerDAO){
        panel.chair = reviewer
        panels.save(panel)
    }

    @Transactional
    fun deletePanelChair(panel: PanelDAO){
        panel.chair = null
        panels.save(panel)
    }

    @Transactional
    fun getReviewers(panel: PanelDAO): Set<UserDAO.ReviewerDAO> {
        return panel.reviewers
    }

    @Transactional
    fun addReviewerToPanel(panel: PanelDAO, reviewer: UserDAO.ReviewerDAO) {
        reviewer.panels.add(panel)
        //panel.reviewers.add(reviewer)
        reviewers.save(reviewer)
    }

    @Transactional
    fun deleteReviewerFromPanel(panel: PanelDAO, reviewer: UserDAO.ReviewerDAO) {
       // panel.reviewers.remove(reviewer)
        reviewer.panels.remove(panel)
        reviewers.save(reviewer)
    }

    /* Data item handling */
    @Transactional
    fun getAllDataItems(call: GrantCallDAO): Set<DataItemDAO> {
        return call.dataItems
    }

    @Transactional
    fun getOneDataItem(call: GrantCallDAO, dataItemId: Long): DataItemDAO {
        return dataItems.findById(dataItemId).orElseThrow {
            NotFoundException("Data Item with id $dataItemId not found")
        }
    }

    @Transactional
    fun addDataItem(call: GrantCallDAO, dataItem: DataItemDAO) {
        call.dataItems.add(dataItem)
        calls.save(call)
    }

    @Transactional
    fun deleteDataItem(call: GrantCallDAO, dataItem: DataItemDAO) {
        call.dataItems.remove(dataItem)
        calls.save(call)
    }


}