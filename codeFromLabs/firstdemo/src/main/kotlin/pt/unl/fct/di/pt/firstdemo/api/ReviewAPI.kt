package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*

@RequestMapping("/reviews")
interface ReviewAPI {
    @GetMapping
    fun getAll(): List<ReviewDTO>

    @GetMapping("/{id}")
    fun getOne(@PathVariable id:Long): ReviewDTO

    @PutMapping("/{id}")
    fun editReview(@PathVariable id:Long)

    @DeleteMapping("{id}")
    fun deleteReview(@PathVariable id:Long)
}