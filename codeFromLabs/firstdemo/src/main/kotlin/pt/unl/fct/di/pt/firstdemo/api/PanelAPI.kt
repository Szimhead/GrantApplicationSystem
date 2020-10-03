package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*

@RequestMapping("/panel")
interface PanelAPI {

    @GetMapping
    fun getAll():List<PanelDTO>

    @GetMapping("/{id}")
    fun getOne(@PathVariable id:Long):PanelDTO

    @PutMapping("/{id}")
    fun editPanel(@PathVariable id:Long)

    @DeleteMapping("{id}")
    fun deletePanel(@PathVariable id:Long)

    /* Reviewer handling */
    @GetMapping("/{id}/reviewers")
    fun getReviewers(@PathVariable id: Long): List<ReviewerDTO>

    @PostMapping("/{id}/reviewers/{reviewerId}")
    fun addReviewerToPanel(@PathVariable id:Long, @PathVariable reviewerId: Long)

    @DeleteMapping("/{panelId}/reviewers/{reviewerId}")
    fun deleteReviewerFromPanel(@PathVariable panelId: Long, @PathVariable reviewerId:Long)
}