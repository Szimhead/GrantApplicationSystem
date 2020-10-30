package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.*

@RestController
class GrantCallController(
        val calls:GrantCallService,
        val studs: StudentService,
        val revs: ReviewerService,
        val apps: ApplicationService,
        val users: UserService): GrantCallAPI {

    @PreAuthorize("hasRole({'ADMIN'}) or hasRole({'REVIEWER'}) or hasRole({'CHAIR'}) or hasRole({'STUDENT'}) or hasRole({'SPONSOR'})")
    override fun getAll(): Set<GrantCallDTO> = calls.getAll().map { GrantCallDTO(it) }.toSet()

    @PreAuthorize("hasRole({'ADMIN'}) or hasRole({'REVIEWER'}) or hasRole({'CHAIR'}) or hasRole({'STUDENT'}) or hasRole({'SPONSOR'})")
    override fun getAllOpen() = calls.getAllOpen().map { GrantCallDTO(it) }

    @PreAuthorize("hasRole({'ADMIN'}) or hasRole({'REVIEWER'}) or hasRole({'CHAIR'}) or hasRole({'STUDENT'}) or hasRole({'SPONSOR'})")
    override fun getOne(id: Long) = GrantCallDTO(calls.getOne(id))

    override fun addCall(call: GrantCallDTO) = calls.addCall(GrantCallDAO(call, spons.getOne(call.sponsorId)))

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canEditGrantCall(principal,#id) and hasRole({'SPONSOR'}))")
    override fun editCall(id: Long, call: GrantCallDTO) = calls.editCall(calls.getOne(id), GrantCallDAO(call))

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canDeleteGrantCall(principal,#id) and hasRole({'SPONSOR'}))")
    override fun deleteCall(id: Long) = calls.deleteCall(calls.getOne(id))

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canGetGrantApplicationscanGetGrantApplications(principal,#id) and hasRole({'SPONSOR'}))")
    override fun getAllApplicationsFromGrantCall(id: Long): Set<ApplicationDTO> = calls.getCallApplications(calls.getOne(id)).map { ApplicationDTO(it) }.toSet()

    @PreAuthorize("hasRole({'ADMIN'}) or hasRole({'STUDENT'})")
    override fun addApplication(id: Long, app: ApplicationDTO) = calls.addApplication(ApplicationDAO(app, calls.getOne(id), studs.getOne(app.studentId)))

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canDeleteApplication(principal,#appId) and hasRole({'STUDENT'}))")
    override fun deleteApplication(id: Long, appId: Long) = calls.deleteApplication(calls.getOne(id), apps.getOne(appId))

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canEditGrantCall(principal,#id) and hasRole({'SPONSOR'}))")
    override fun getPanelFromGrantCall(id: Long) = PanelDTO(calls.getPanelFromGrantCall(calls.getOne(id)))

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canAddPanelChair(principal,#id) and hasRole({'SPONSOR'}))")
    override fun addPanelChair(id: Long, reviewerId: Long) {
        val reviewer = revs.getOne(reviewerId)
        calls.setPanelChair(calls.getPanelFromGrantCall(calls.getOne(id)), reviewer)
        users.changeRole(users.findUser(reviewer.email), "CHAIR")
    }

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canEditPanelChair(principal,#id) and hasRole({'SPONSOR'}))")
    override fun editPanelChair(id: Long, reviewerId: Long) {
        val oldChair = calls.getPanelFromGrantCall(calls.getOne(id)).chair
        val newChair = revs.getOne(reviewerId)
        calls.setPanelChair(calls.getPanelFromGrantCall(calls.getOne(id)), newChair)
        users.changeRole(users.findUser(newChair.email), "CHAIR")
        if (oldChair!=null) users.changeRole(users.findUser(oldChair.email), "REVIEWER")
    }

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canDeletePanelChair(principal,#id) and hasRole({'SPONSOR'}))")
    override fun deletePanelChair(id: Long) {
        val reviewer = calls.getPanelFromGrantCall(calls.getOne(id)).chair
        calls.deletePanelChair(calls.getPanelFromGrantCall(calls.getOne(id)))
        if (reviewer!=null) users.changeRole(users.findUser(reviewer.email), "REVIEWER")
    }

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canGetReviewersFromGrantCall(principal,#id) and (hasRole({'SPONSOR'}) or hasRole({'CHAIR'})))")
    override fun getReviewers(id: Long) = calls.getReviewers(calls.getPanelFromGrantCall(calls.getOne(id))).map { UserDTO(it) }

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canAddReviewersToGrantCall(principal,#id) and (hasRole({'SPONSOR'}) or hasRole({'CHAIR'})))")
    override fun addReviewerToPanel(id: Long, reviewerId: Long) = calls.addReviewerToPanel(calls.getPanelFromGrantCall(calls.getOne(id)), revs.getOne(reviewerId))

    @PreAuthorize("hasRole({'ADMIN'}) or (SecurityService.canDeleteReviewersFromGrantCall(principal,#id) and (hasRole({'SPONSOR'}) or hasRole({'CHAIR'})))")
    override fun deleteReviewerFromPanel(id: Long, reviewerId:Long) = calls.deleteReviewerFromPanel(calls.getPanelFromGrantCall(calls.getOne(id)), revs.getOne(reviewerId))

    //Data Items
    override fun getAllDataItems(id: Long) = calls.getAllDataItems(calls.getOne(id)).map { DataItemDTO(it) }

    override fun getOneDataItem(id: Long, dataItemId: Long) = DataItemDTO(calls.getOneDataItem(calls.getOne(id), dataItemId))

    override fun addDataItem(id: Long, dataItem: DataItemDTO) = calls.addDataItem(calls.getOne(id), DataItemDAO(dataItem))

    override fun deleteDataItem(id: Long, dataItemId: Long) {
        val call = calls.getOne(id)
        calls.deleteDataItem(call, calls.getOneDataItem(call, dataItemId))
    }
}