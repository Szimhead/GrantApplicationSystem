package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.SponsorService

class SponsorController(val sponsors: SponsorService): SponsorAPI {
    override fun getAll() = sponsors.getAll()

    override fun getOne(id:Long) = sponsors.getOne(id)

    override fun addSponsor() = sponsors.addSponsor()

    override fun deleteSponsor(id:Long) = sponsors.deleteSponsor(id)

    override fun editSponsor(id:Long) = sponsors.editSponsor(id)


    /* grant call handling */
    override fun getGrantCalls(id:Long) = sponsors.getGrantCalls(id)

    override fun addGrantCall(id:Long) = sponsors.addGrantCall(id)
}