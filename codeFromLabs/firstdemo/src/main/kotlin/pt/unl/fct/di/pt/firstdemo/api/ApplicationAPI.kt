package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/applications")
interface ApplicationAPI {

    @GetMapping("")
    fun getAll():List<ApplicationDTO>

    @GetMapping("/{id}")
    fun getOne(@PathVariable id:Long): ApplicationDTO

    /* Review handling */
    @GetMapping("{id}/reviews")
    fun getAllReviews(@PathVariable id: Long): List<ReviewDTO>

    @GetMapping("/{app_id}/reviews/{review_id}")
    fun getReview(@PathVariable app_id:Long, @PathVariable review_id:Long): ReviewDTO

    @PostMapping("/{app_id}/reviews/{review_id}")
    fun addReview(@PathVariable app_id:Long, @PathVariable review_id:Long)

    /* Final Evaluation handling */
    @GetMapping("/{id}/final")
    fun getFinal(@PathVariable id: Long): FinalDTO   // default final status should be "pending" or something

}
