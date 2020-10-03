package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/final")
class FinalController(val finals: FinalService): FinalEvaluationAPI {

    @GetMapping
    override fun getAll(): ??? {
        TODO("Not yet implemented")
    }

    @GetMapping
    override fun getOne(id: Long): FinalDTO {
        TODO("Not yet implemented")
    }

    @PostMapping
    override fun addFinal(final: Long) {
        TODO("Not yet implemented")
    }

    @PutMapping
    override fun editFinal(id: Long) {
        TODO("Not yet implemented")
    }

    @DeleteMapping
    override fun deleteFinal(id: Long) {
        TODO("Not yet implemented")
    }
}