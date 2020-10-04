package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.CVDTO

@Service
class CVService {
    fun getOne(id:Long) = CVDTO(1)

    fun deleteCV(id:Long) {
        TODO("Not yet implemented")
    }
}