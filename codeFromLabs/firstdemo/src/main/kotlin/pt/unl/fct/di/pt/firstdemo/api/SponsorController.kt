package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.GrantCallDAO
import pt.unl.fct.di.pt.firstdemo.services.UserDAO.SponsorDAO
import pt.unl.fct.di.pt.firstdemo.services.SponsorService
import pt.unl.fct.di.pt.firstdemo.services.UserDAO
import pt.unl.fct.di.pt.firstdemo.services.UserService

@RestController
class SponsorController(val sponsors: SponsorService, val users: UserService): SponsorAPI {

    override fun getAll() = sponsors.getAll().map { OrganizationDTO(it) }

    override fun getOne(id:Long) = OrganizationDTO(sponsors.getOne(id))

    override fun addSponsor(organization: OrganizationDTO) {
        val sponsor = UserDAO.SponsorDAO(organization)
        sponsors.addSponsor(sponsor)
        users.addUser(sponsors.getSponsorUser(sponsor))
    }

    override fun deleteSponsor(id:Long) {
        val sponsor = sponsors.getOne(id)
        sponsors.deleteSponsor(sponsor, users.findUser(sponsor.name))
    }

    override fun editSponsor(id:Long, sponsor: OrganizationDTO) = sponsors.editSponsor(sponsors.getOne(id), UserDAO.SponsorDAO(sponsor))

    /* grant call handling */
    override fun getGrantCalls(id:Long) = sponsors.getGrantCallsFromSponsor(sponsors.getOne(id)).map { GrantCallDTO(it) }

}