package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*

@RequestMapping("/panel")
interface PanelAPI {

    @GetMapping
    fun getAll():<PanelDTO>

    @GetMapping("/{id}")
    fun getOne(@PathVariable id:Long):PanelDTO

    @PostMapping()
    fun addPanel(@RequestParam final:Long)

    @PostMapping("/reviewer_id")
    fun addReviewerToPanel(@PathVariable id:Long)

    @PutMapping("/{id}")
    fun editPanel(@PathVariable id:Long)

    @DeleteMapping("{id}")
    fun deletePanel(@PathVariable id:Long)

    @DeleteMapping("/reviewer_id")
    fun deleteReviewerFromPanel(@PathVariable id: Long)
}