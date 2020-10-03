package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@Service
class PanelService {

    fun getAll() = listOf<PanelDTO>(PanelDTO(...))

    fun getOne(id:Long) = PanelDTO(...)

    fun addPanel(final:Long) = print("add panel działa")

    fun addReviewerToPanel(id:Long) = print("add reviewer to panel działa")

    fun editPanel(id:Long) = print ("edit panel działa")

    fun deletePanel(id:Long) = print ("delete panel działa")

    fun deleteReviewerFromPanel(id: Long) = print ("delete reviewer from panel działa")
}