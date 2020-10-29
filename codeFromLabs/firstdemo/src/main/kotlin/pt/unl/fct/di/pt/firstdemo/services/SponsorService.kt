package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.GrantCallDTO
import pt.unl.fct.di.pt.firstdemo.api.OrganizationDTO
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.GrantCallRepository
import pt.unl.fct.di.pt.firstdemo.model.PanelRepository
import pt.unl.fct.di.pt.firstdemo.model.ReviewerRepository
import pt.unl.fct.di.pt.firstdemo.model.SponsorRepository
import java.util.*
import javax.transaction.Transactional


@Service
class SponsorService (val sponsors: SponsorRepository, val grantCalls: GrantCallRepository){
    fun getAll() : Iterable<SponsorDAO> = sponsors.findAll()

    fun getOne(id:Long) : SponsorDAO = sponsors.findById(id).orElseThrow{
        NotFoundException("Sponsor with $id not found")
    }

    fun addSponsor(sponsor: SponsorDAO)  {
        sponsors.save(sponsor)
    }

    fun deleteSponsor(sponsor: SponsorDAO) {
        sponsors.delete(sponsor);
    }

    @Transactional
    fun editSponsor(editedSponsor: SponsorDAO, newSponsor: SponsorDAO) {
        editedSponsor.contact = newSponsor.contact
        editedSponsor.name = newSponsor.name
    }

    /* grant call handling */
    @Transactional
    fun getGrantCallsFromSponsor(sponsor: SponsorDAO) : Iterable<GrantCallDAO> {
        return sponsor.grantCalls
    }

    @Transactional
    fun addGrantCall(sponsor: SponsorDAO, grantCall: GrantCallDAO) {
        var panel = PanelDAO(grantCall)
        grantCall.panel = panel
        grantCalls.save(grantCall)
    }
}