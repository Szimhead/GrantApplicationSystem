package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.GrantCallDTO
import pt.unl.fct.di.pt.firstdemo.api.OrganizationDTO
import java.util.*


@Service
class SponsorService {
    fun getAll() = listOf(OrganizationDTO(1, "Firm1", "no contact"))

    fun getOne(id:Long) = OrganizationDTO(2, "Firm2", "no contact")

    fun addSponsor(id: Long) {
        TODO("Not yet implemented")
    }

    fun deleteSponsor(id:Long) {
        TODO("Not yet implemented")
    }

    fun editSponsor(id:Long) {
        TODO("Not yet implemented")
    }


    /* grant call handling */
    fun getGrantCalls(id:Long) = listOf(GrantCallDTO("all success", "some grant call", 500000.00,
            Date(),  Date()))

    fun addGrantCall(id:Long, title: String) {
        TODO("Not yet implemented")
    }
}