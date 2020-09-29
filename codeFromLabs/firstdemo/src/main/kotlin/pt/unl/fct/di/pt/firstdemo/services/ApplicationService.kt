package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.ApplicationDTO
import java.util.*

@Service
class ApplicationService {

    fun getAll() = listOf<ApplicationDTO>(ApplicationDTO(1, Date(), 0))

    fun getOne(id:Long) = ApplicationDTO(2, Date(), 0)
}