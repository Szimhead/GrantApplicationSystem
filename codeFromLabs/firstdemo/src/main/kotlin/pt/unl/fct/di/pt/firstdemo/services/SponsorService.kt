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
    fun getAll() : Iterable<SponsorDAO> = sponsors.findAll()

    fun getOne(id:Long) : SponsorDAO = sponsors.findById(id).orElseThrow{
        NotFoundException("Sponsor with $id not found")
    }

    fun addSponsor(sponsor: SponsorDAO)  {
        sponsors.save(sponsor)
    }

    fun deleteSponsor(sponsor: SponsorDAO, user: UserDAO) {
        users.delete(user)
        sponsors.delete(sponsor)
    }

    @Transactional
    fun editSponsor(editedSponsor: SponsorDAO, newSponsor: SponsorDAO) {
        editedSponsor.contact = newSponsor.contact
    }

    /* grant call handling */
    @Transactional
    fun getGrantCallsFromSponsor(sponsor: SponsorDAO) : Iterable<GrantCallDAO> {
        return sponsor.grantCalls
    }

    @Transactional
    fun addGrantCall(sponsor: SponsorDAO, grantCall: GrantCallDAO) {
        val panel = PanelDAO(grantCall)
        grantCall.panel = panel
        grantCalls.save(grantCall)
    }

    fun getSponsorUser(sponsor: SponsorDAO) : UserDAO{
        return UserDAO(sponsor.name,"password","SPONSOR")
    }
}