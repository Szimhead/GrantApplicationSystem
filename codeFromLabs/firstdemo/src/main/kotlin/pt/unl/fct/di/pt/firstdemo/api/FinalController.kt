package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.FinalService

@RestController
@RequestMapping("/final")
class FinalController(val finals: FinalService): FinalEvaluationAPI {


    override fun getAll(): ??? {
        TODO("Not yet implemented")
    }


    override fun getOne(id: Long): FinalDTO {
        TODO("Not yet implemented")
    }


    override fun addFinal(final: Long) {
        TODO("Not yet implemented")
    }


    override fun editFinal(id: Long) {
        TODO("Not yet implemented")
    }


    override fun deleteFinal(id: Long) {
        TODO("Not yet implemented")
    }
}