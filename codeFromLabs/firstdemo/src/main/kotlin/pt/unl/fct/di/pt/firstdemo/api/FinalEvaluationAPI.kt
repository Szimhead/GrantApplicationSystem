package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*

@RequestMapping("/finals")
interface FinalEvaluationAPI {

    @GetMapping
    fun getAll(): List<FinalDTO>

    @GetMapping("/{id}")
    fun getOne(@PathVariable id:Long):FinalDTO

    @PostMapping()
    fun addFinal(@RequestParam final:Long)

    @PutMapping("/{id}")
    fun editFinal(@PathVariable id:Long)

    @DeleteMapping("{id}")
    fun deleteFinal(@PathVariable id:Long)
}