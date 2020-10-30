package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.GrantCallDTO
import pt.unl.fct.di.pt.firstdemo.api.OrganizationDTO
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.*
import java.util.*
import javax.transaction.Transactional


@Service
class SponsorService (val sponsors: SponsorRepository, val grantCalls: GrantCallRepository, val users: UserRepository){
    fun getAll() : Iterable<UserDAO.SponsorDAO> = sponsors.findAll()

    fun getOne(id:Long) : UserDAO.SponsorDAO = sponsors.findById(id).orElseThrow{
        NotFoundException("Sponsor with $id not found")
    }

    fun addSponsor(sponsor: UserDAO.SponsorDAO)  {
        sponsors.save(sponsor)
    }

    fun deleteSponsor(sponsor: UserDAO.SponsorDAO, user: UserDAO) {
        users.delete(user)
        sponsors.delete(sponsor)
    }

    @Transactional
    fun editSponsor(editedSponsor: UserDAO.SponsorDAO, newSponsor: UserDAO.SponsorDAO) {
        editedSponsor.contact = newSponsor.contact
        editedSponsor.name = newSponsor.name
        sponsors.save(editedSponsor)
    }

    /* grant call handling */
    @Transactional
    fun getGrantCallsFromSponsor(sponsor: UserDAO.SponsorDAO) : Iterable<GrantCallDAO> {
        return sponsor.grantCalls
    }

    @Transactional
    fun addGrantCall(sponsor: UserDAO.SponsorDAO, grantCall: GrantCallDAO) {
        val panel = PanelDAO(grantCall)
        grantCall.panel = panel
        grantCalls.save(grantCall)
    }

    fun getSponsorUser(sponsor: UserDAO.SponsorDAO) : UserDAO{
        return UserDAO(sponsor.name,"password","SPONSOR")
    }
}