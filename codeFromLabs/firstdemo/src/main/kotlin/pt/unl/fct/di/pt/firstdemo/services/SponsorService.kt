package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.GrantCallDTO
import pt.unl.fct.di.pt.firstdemo.api.SponsorDTO

@Service
class SponsorService {
    fun getAll() = listOf(SponsorDTO(1, "Firm1", "no contact"))

    fun getOne(id:Long) = SponsorDTO(2, "Firm2", "no contact")

    fun addSponsor() {
        TODO("Not yet implemented")
    }

    fun deleteSponsor(id:Long) {
        TODO("Not yet implemented")
    }

    fun editSponsor(id:Long) {
        TODO("Not yet implemented")
    }


    /* grant call handling */
    fun getGrantCalls(id:Long) = listOf(GrantCallDTO("research grant"))

    fun addGrantCall(id:Long) {
        TODO("Not yet implemented")
    }
}