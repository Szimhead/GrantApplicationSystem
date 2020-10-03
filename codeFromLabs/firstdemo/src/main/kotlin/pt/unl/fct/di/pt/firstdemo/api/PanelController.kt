package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.di.pt.firstdemo.services.PanelService

@RestController
class PanelController(val panels: PanelService): PanelAPI {

    override fun getAll() = panels.getAll()

    override fun getOne(id: Long) = panels.getOne(id)

    override fun editPanel(id: Long) = panels.editPanel(id)

    override fun deletePanel(id: Long) = panels.deletePanel(id)

    override fun getReviewers(id: Long) = panels.getReviewers(id)

    override fun addReviewerToPanel(id: Long, reviewerId: Long) = panels.addReviewerToPanel(id, reviewerId)

    override fun deleteReviewerFromPanel(panelId: Long, reviewerId:Long) = panels.deleteReviewerFromPanel(panelId,reviewerId)

}