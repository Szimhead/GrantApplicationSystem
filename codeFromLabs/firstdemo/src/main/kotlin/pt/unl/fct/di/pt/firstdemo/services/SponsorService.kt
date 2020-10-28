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
class SponsorService ( val sponsors: SponsorRepository, val grandCalls: GrantCallRepository){
    fun getAll() : Iterable<SponsorDAO> = sponsors.findAll()

    fun getOne(id:Long) : SponsorDAO = sponsors.findById(id).orElseThrow{
        NotFoundException("Sponsor with $id not found")
    }

    fun addSponsor(sponsor: SponsorDAO)  {
        sponsor.id=0
        sponsors.save(sponsor)
    }

    fun deleteSponsor(id:Long) {
        val sponsor = sponsors.findById(id).orElseThrow{
            NotFoundException("Sponsor with $id not found")
        }
        sponsors.delete(sponsor);
    }

    @Transactional
    fun editSponsor(id:Long, sponsor: SponsorDAO) {
        val editedSponsor = sponsors.findById(id).orElseThrow{
            NotFoundException("Sponsor with $id not found")
        };
        editedSponsor.contact=sponsor.contact
        editedSponsor.name =sponsor.name

        sponsors.save(editedSponsor)
    }


    /* grant call handling */
    @Transactional
    fun getGrantCalls(id:Long) : Iterable<GrantCallDAO> {
        val sponsor = sponsors.findById(id).orElseThrow{
            NotFoundException("Sponsor with $id not found")
        }
        return sponsor.grantCalls
    }

    @Transactional
    fun addGrantCall(id:Long, grantCall: GrantCallDAO) {
        val sponsor = sponsors.findById(id).orElseThrow{
            NotFoundException("Sponsor with $id not found")
        }
        grantCall.id=0
        sponsor.grantCalls.add(grantCall)
    }
}