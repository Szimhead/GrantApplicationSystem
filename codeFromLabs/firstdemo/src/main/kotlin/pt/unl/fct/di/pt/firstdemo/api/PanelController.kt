package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/final")
class PanelController(val panels:PanelService): PanelAPI {
    override fun getAll(): ??? {
        TODO("Not yet implemented")
    }

    override fun getOne(id: Long): PanelDTO {
        TODO("Not yet implemented")
    }

    override fun addPanel(final: Long) {
        TODO("Not yet implemented")
    }

    override fun addReviewerToPanel(id: Long) {
        TODO("Not yet implemented")
    }

    override fun editPanel(id: Long) {
        TODO("Not yet implemented")
    }

    override fun deletePanel(id: Long) {
        TODO("Not yet implemented")
    }

    override fun deleteReviewerFromPanel(id: Long) {
        TODO("Not yet implemented")
    }

}