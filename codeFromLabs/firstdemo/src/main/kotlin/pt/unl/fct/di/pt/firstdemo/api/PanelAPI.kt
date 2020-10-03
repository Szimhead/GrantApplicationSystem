package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*

@RequestMapping("/panel")
interface PanelAPI {

    @GetMapping
    fun getAll():List<PanelDTO>

    @GetMapping("/{id}")
    fun getOne(@PathVariable id:Long):PanelDTO

    @PostMapping()
    fun addPanel()

    @PostMapping("/id")
    fun addReviewerToPanel(@PathVariable id:Long)

    @PutMapping("/{id}")
    fun editPanel(@PathVariable id:Long)

    @DeleteMapping("{id}")
    fun deletePanel(@PathVariable id:Long)

    @DeleteMapping("/panel_id/reviewer_id")
    fun deleteReviewerFromPanel(@PathVariable panelId: Long, @PathVariable reviewerId:Long)
}