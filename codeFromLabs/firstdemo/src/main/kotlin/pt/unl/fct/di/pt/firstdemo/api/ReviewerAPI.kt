package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*

@RequestMapping("/reviewers")
interface ReviewerAPI {

    @GetMapping("")
    fun getAll():List<ReviewerDTO>

    @GetMapping("/{reviewerNr}")
    fun getOne(@PathVariable reviewerNr:Long): ReviewerDTO

    @DeleteMapping("/{reviewerNr}")
    fun deleteReviewer(@PathVariable reviewerNr: Long)

    @PutMapping("{reviewerNr}")
    fun editReviewer(@PathVariable reviewerNr: Long)


    /* panel handling */
    @GetMapping("/{reviewerNr}/panels")
    fun getPanels(@PathVariable reviewerNr: Long):List<PanelDTO>

    @GetMapping("/{reviewerNr}/panels/{p_id}")
    fun getOnePanel(@PathVariable reviewerNr: Long, @PathVariable p_id:Long): PanelDTO


    /* reviews handling */
    @GetMapping("/{reviewerNr}/reviews")
    fun getReviews(@PathVariable reviewerNr: Long):List<ReviewDTO>

    @GetMapping("/{reviewerNr}/reviews/{r_id}")
    fun getOneReview(@PathVariable reviewerNr: Long, @PathVariable r_id:Long): ReviewDTO
}