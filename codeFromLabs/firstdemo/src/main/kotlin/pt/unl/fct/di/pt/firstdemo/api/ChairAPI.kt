package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*

@RequestMapping("/chair")
interface ChairAPI {

    @GetMapping
    fun getAll():<ChairDTO>

    @GetMapping("/{id}")
    fun getOne(@PathVariable id:Long):ChairDTO

    @PostMapping()
    fun addChair(@RequestParam final:Long)

    @PutMapping("/{id}")
    fun editChair(@PathVariable id:Long)

    @DeleteMapping("{id}")
    fun deleteChair(@PathVariable id:Long)
}