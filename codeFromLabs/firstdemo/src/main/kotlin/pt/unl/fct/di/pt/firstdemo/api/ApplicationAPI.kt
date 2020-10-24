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


    @ApiOperation(value = "Delete Application by id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully delete application."),
        ApiResponse(code = 401, message = "Not authorized to delete application!"),
        ApiResponse(code = 403, message = "Delete application forbidden."),
        ApiResponse(code = 404, message = "Application not found.")
    ])
    @DeleteMapping("/{id}")
    fun deleteApplication( @ApiParam(name = "id", type = "Long", value = "The id of the application", required = true)
                           @PathVariable id: Long)


    @ApiOperation(value = "Edit Application by id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully edit application."),
        ApiResponse(code = 401, message = "Not authorized to edit application!"),
        ApiResponse(code = 403, message = "Edit application forbidden."),
        ApiResponse(code = 404, message = "Application not found.")
    ])
    @PutMapping("/{id}")
    fun editApplication(@ApiParam(name = "id", type = "Long", value = "The id of the application", required = true)
                        @PathVariable id: Long, @RequestBody app: ApplicationDTO)


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


    @ApiOperation(value = "Get review by id in Application with a given id", response = ReviewDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved review from application."),
        ApiResponse(code = 401, message = "Not authorized to get review from application!"),
        ApiResponse(code = 403, message = "Get review from application forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @GetMapping("{id}/reviews/{reviewId}")
    fun getOneReview(@ApiParam(name = "id", type = "Long", value = "The id of the application to get the review from", required = true)
                  @PathVariable id:Long,
                  @ApiParam(name = "reviewId", type = "Long", value = "The id of the review to get", required = true)
                  @PathVariable reviewId:Long): ReviewDTO

    @ApiOperation(value = "Delete review by id in Application with a given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully deleted review from application."),
        ApiResponse(code = 401, message = "Not authorized to delete review from application!"),
        ApiResponse(code = 403, message = "Delete review from application forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @DeleteMapping("{id}/reviews/{reviewId}")
    fun deleteReview(@ApiParam(name = "id", type = "Long", value = "The id of the application to delete the review from", required = true)
                     @PathVariable id:Long, @ApiParam(name = "reviewId", type = "Long", value = "The id of the review to delete", required = true)
                     @PathVariable reviewId: Long)


    @ApiOperation(value = "Edit review by id in Application with a given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully edited review from application."),
        ApiResponse(code = 401, message = "Not authorized to edit review from application!"),
        ApiResponse(code = 403, message = "Edit review from application forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @PutMapping("{id}/reviews")
    fun editReview(@ApiParam(name = "id", type = "Long", value = "The id of the application to edit the review from", required = true)
                   @PathVariable id:Long,
                   @ApiParam(name = "reviewId", type = "Long", value = "The id of the review to edit", required = true)
                   @RequestBody review:ReviewDTO)


    @ApiOperation(value = "Adds a review to an application with a given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added review to application."),
        ApiResponse(code = 401, message = "Not authorized to add review to application!"),
        ApiResponse(code = 403, message = "Add review to application forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @PostMapping("{id}/reviews")
    fun addReview(@ApiParam(name = "id", type = "Long", value = "The id of the application to add the review to", required = true)
                  @PathVariable id:Long,
                  @ApiParam(name = "review_id", type = "Long", value = "The id of the review to be created", required = true) //not clear, that is not an id, it is a review
                  @RequestBody review:ReviewDTO)

    /* Answer handling */
    @ApiOperation(value = "Get list of all Answers in Application with a given id", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all answers from application."),
        ApiResponse(code = 401, message = "Not authorized to get answers from application!"),
        ApiResponse(code = 403, message = "Get all answers from application forbidden."),
        ApiResponse(code = 404, message = "Application not found.")
    ])
    @GetMapping("/{id}/answers")
    fun getAllAnswers(@ApiParam(name = "id", type = "Long", value = "The id of the application to get the answers from", required = true)
                      @PathVariable id:Long): List<AnswerDTO>

    @ApiOperation(value = "Get answer by name in Application with a given id", response = ReviewDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved answer from application."),
        ApiResponse(code = 401, message = "Not authorized to get answer from application!"),
        ApiResponse(code = 403, message = "Get answer from application forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @GetMapping("/{id}/answers/{answerId}")
    fun getOneAnswer(@ApiParam(name = "id", type = "Long", value = "The id of the application to get the answer from", required = true)
                     @PathVariable id:Long, @ApiParam(name = "answerId", type = "String", value = "The id of the answer to get", required = true)
                     @PathVariable answerId: Long): AnswerDTO


    @ApiOperation(value = "Adds an answer to an application with a given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added answer to application."),
        ApiResponse(code = 401, message = "Not authorized to add answer to application!"),
        ApiResponse(code = 403, message = "Add answer to application forbidden."),
        ApiResponse(code = 404, message = "Application not found.")
    ])
    @PostMapping("/{id}/answers")
    fun addAnswer(@ApiParam(name = "id", type = "Long", value = "The id of the application to add the answer to", required = true)
                   @PathVariable id:Long, @ApiParam(name = "name", type = "String", value = "The name of the answer to add", required = true)
                   @RequestBody answer:AnswerDTO)

    @ApiOperation(value = "Edit answer by name in Application with a given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully edited answer from application."),
        ApiResponse(code = 401, message = "Not authorized to edit answer from application!"),
        ApiResponse(code = 403, message = "Edit answer from application forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @PutMapping("/{id}/answers")
    fun editAnswer(@ApiParam(name = "id", type = "Long", value = "The id of the application to edit the answer from", required = true)
                   @PathVariable id:Long, @ApiParam(name = "name", type = "String", value = "The name of the answer to edit", required = true)
                   @RequestBody answer:AnswerDTO)

    @ApiOperation(value = "Delete answer by name in Application with a given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully deleted answer from application."),
        ApiResponse(code = 401, message = "Not authorized to delete answer from application!"),
        ApiResponse(code = 403, message = "Delete answer from application forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @DeleteMapping("/{id}/answers/{answerId}")
    fun deleteAnswer(@ApiParam(name = "id", type = "Long", value = "The id of the application to delete the answer from", required = true)
                     @PathVariable id:Long, @ApiParam(name = "answerId", type = "String", value = "The id of the answer to get", required = true)
                     @PathVariable answerId: Long)

}
