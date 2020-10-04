package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.FinalService

@RestController
class FinalController(val finals: FinalService): FinalEvaluationAPI {

    override fun getAll() = finals.getAll()

    override fun getOne(id: Long) = finals.getOne(id)

    override fun addFinal(final: Long) = finals.addFinal(final)

    override fun editFinal(id: Long) = finals.editFinal(id)

    override fun deleteFinal(id: Long) = finals.deleteFinal(id)
}