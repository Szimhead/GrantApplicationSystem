package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*

@RequestMapping("/reviews")
interface ReviewAPI {

    @ApiOperation(value = "Get list of all Reviews", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all reviews."),
        ApiResponse(code = 401, message = "Not authorized to get reviews!"),
        ApiResponse(code = 403, message = "Get all reviews forbidden.")
    ])
    @GetMapping
    fun getAll(): List<ReviewDTO>

    @ApiOperation(value = "Get Review by id", response = ReviewDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved review."),
        ApiResponse(code = 401, message = "Not authorized to get review!"),
        ApiResponse(code = 403, message = "Get review forbidden."),
        ApiResponse(code = 404, message = "Review not found.")
    ])
    @GetMapping("/{id}")
    fun getOne(@ApiParam(name = "id", type = "Long", value = "The id of the review", required = true)
               @PathVariable id:Long): ReviewDTO

    @ApiOperation(value = "Edit Review with given id", response = Iterable::class)
    @PutMapping("/{id}")
    fun editReview(@PathVariable id:Long)

    @ApiOperation(value = "Delete Review with given id", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved review."),
        ApiResponse(code = 401, message = "Not authorized to get review!"),
        ApiResponse(code = 403, message = "Get review forbidden."),
        ApiResponse(code = 404, message = "Review not found.")
    ])
    @DeleteMapping("{id}")
    fun deleteReview(@PathVariable id:Long)

    @ApiOperation(value = "Get list of all Reviews in Application with a given id", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all reviews from application."),
        ApiResponse(code = 401, message = "Not authorized to get reviews from application!"),
        ApiResponse(code = 403, message = "Get all reviews from application forbidden."),
        ApiResponse(code = 404, message = "Application not found.")
    ])
    @GetMapping("/applicaton/{id}")
    fun getAllReviewsFromApplication(@ApiParam(
            name = "id",
            type = "Long",
            value = "The id of the application to get all Reviews from",
            required = true) @PathVariable id: Long): List<ReviewDTO>

    @ApiOperation(value = "Adds a review to an application with a given id", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all reviews from application."),
        ApiResponse(code = 401, message = "Not authorized!"),
        ApiResponse(code = 403, message = "Forbidden."),
        ApiResponse(code = 404, message = "Application not found.")
    ])
    @PostMapping("/{review_id}/application/{app_id}")
    fun addReview(@ApiParam(name = "review_id", type = "Long", value = "The id of the review to be created", required = true)
                  @PathVariable review_id:Long,
                  @ApiParam(name = "app_id", type = "Long", value = "The id of the application to add the review to", required = true)
                  @PathVariable app_id:Long)
}