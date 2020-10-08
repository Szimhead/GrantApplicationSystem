package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*
import kotlin.collections.*
import java.util.*

@Api(value="Grant Management - Applications API",
        description="Operation management of Applications")

@RequestMapping("/applications")
interface ApplicationAPI {

    @ApiOperation(value = "Get list of all Applications", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all applications."),
        ApiResponse(code = 401, message = "Not authorized to get applications!"),
        ApiResponse(code = 403, message = "Get all applications forbidden.")
    ])
    @GetMapping("")
    fun getAll():List<ApplicationDTO>

    @ApiOperation(value = "Get Application by id", response = ApplicationDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved application."),
        ApiResponse(code = 401, message = "Not authorized to get application!"),
        ApiResponse(code = 403, message = "Get application forbidden."),
        ApiResponse(code = 404, message = "Application not found.")
    ])
    @GetMapping("/{id}")
    fun getOne( @ApiParam(name = "id", type = "Long", value = "The id of the application", required = true)
                @PathVariable id:Long): ApplicationDTO

    @DeleteMapping("{id}")
    fun deleteApplication(id: Long)

    @PutMapping("{id}")
    fun editApplication(id:Long)


    /* Review handling */
    @ApiOperation(value = "Get list of all Reviews in Application with a given id", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all reviews from application."),
        ApiResponse(code = 401, message = "Not authorized to get reviews from application!"),
        ApiResponse(code = 403, message = "Get all reviews from application forbidden."),
        ApiResponse(code = 404, message = "Application not found.")
    ])
    @GetMapping("/{id}/reviews")
    fun getAllReviewsFromApplication(@ApiParam(
            name = "id",
            type = "Long",
            value = "The id of the application to get all Reviews from",
            required = true) @PathVariable id: Long): List<ReviewDTO>

    /* --------- TODO add documentation here ------------- */
    @GetMapping("{id}/reviews/{review_id}")
    fun getOneReview(@ApiParam(name = "id", type = "Long", value = "The id of the application to add the review to", required = true)
                  @PathVariable id:Long,
                  @ApiParam(name = "review_id", type = "Long", value = "The id of the review to be created", required = true)
                  @PathVariable review_id:Long): ReviewDTO

    @DeleteMapping("{id}/reviews/{review_id}")
    fun deleteReview(@PathVariable id:Long, @PathVariable review_id: Long)

    @PutMapping("{id}/reviews/{review_id}")
    fun editReview(@PathVariable id:Long, @PathVariable review_id: Long)

    @ApiOperation(value = "Adds a review to an application with a given id", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all reviews from application."),
        ApiResponse(code = 401, message = "Not authorized!"),
        ApiResponse(code = 403, message = "Forbidden."),
        ApiResponse(code = 404, message = "Application not found.")
    ])
    @PostMapping("{id}/reviews/{review_id}")
    fun addReview(@ApiParam(name = "id", type = "Long", value = "The id of the application to add the review to", required = true)
                  @PathVariable id:Long,
                  @ApiParam(name = "review_id", type = "Long", value = "The id of the review to be created", required = true)
                  @PathVariable review_id:Long)

    /* Answer handling */
    @GetMapping("/{id}/answers")
    fun getAllAnswers(@PathVariable id:Long): List<AnswerDTO>

    @GetMapping("/{id}/answers/name")
    fun getOneAnswer(@PathVariable id:Long, @PathVariable name: String): AnswerDTO

    @PostMapping("/{id}/answers")
    fun addAnswer(@PathVariable id:Long)

    @PutMapping("/{id}/answers/name")
    fun editAnswer(@PathVariable id:Long, @PathVariable name: String)

    @DeleteMapping("/{id}/answers/name")
    fun deleteAnswer(@PathVariable id:Long, @PathVariable name: String)

}
