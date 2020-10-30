package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.GrantCallDAO
import pt.unl.fct.di.pt.firstdemo.services.SponsorDAO
import pt.unl.fct.di.pt.firstdemo.services.SponsorService

@RestController
class SponsorController(val sponsors: SponsorService): SponsorAPI {

    override fun getAll() = sponsors.getAll().map { OrganizationDTO(it) }

    override fun getOne(id:Long) = OrganizationDTO(sponsors.getOne(id))

    override fun addSponsor(sponsor: OrganizationDTO) = sponsors.addSponsor(SponsorDAO(sponsor))

    override fun deleteSponsor(id:Long) = sponsors.deleteSponsor(sponsors.getOne(id))

    override fun editSponsor(id:Long, sponsor: OrganizationDTO) = sponsors.editSponsor(sponsors.getOne(id), SponsorDAO(sponsor))

    /* grant call handling */
    override fun getGrantCalls(id:Long) = sponsors.getGrantCallsFromSponsor(sponsors.getOne(id)).map { GrantCallDTO(it) }

}