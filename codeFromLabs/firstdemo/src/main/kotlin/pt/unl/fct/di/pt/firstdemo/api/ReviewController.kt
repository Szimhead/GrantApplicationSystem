package pt.unl.fct.di.pt.firstdemo.api

import pt.unl.fct.di.pt.firstdemo.services.ReviewService

class ReviewController(val reviews: ReviewService): ReviewAPI {

    override fun getAll() = reviews.getAll()

    override fun getOne(id:Long) = reviews.getOne(id)

    override fun editReview(id:Long) = reviews.editReview(id)

    override fun deleteReview(id:Long) = reviews.deleteReview(id)
}