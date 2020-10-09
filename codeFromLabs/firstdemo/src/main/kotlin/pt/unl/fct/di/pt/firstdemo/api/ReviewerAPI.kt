package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*

@RequestMapping("/reviewers")
interface ReviewerAPI {

    @ApiOperation(value = "Get list of all Reviewers", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all reviewers."),
        ApiResponse(code = 401, message = "Not authorized to get reviewers!"),
        ApiResponse(code = 403, message = "Get all reviewers forbidden.")
    ])
    @GetMapping("")
    fun getAll():List<UserDTO>

    @ApiOperation(value = "Get Reviewer by reviewerNr", response = UserDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved reviewer."),
        ApiResponse(code = 401, message = "Not authorized to get reviewer!"),
        ApiResponse(code = 403, message = "Get reviewer forbidden."),
        ApiResponse(code = 404, message = "Reviewer not found.")
    ])
    @GetMapping("/{reviewerNr}")
    fun getOne(@ApiParam(name = "reviewerNr", type = "Long", value = "The reviewerNr of the reviewer", required = true)
               @PathVariable reviewerNr:Long): UserDTO

    @ApiOperation(value = "Add Reviewer with reviewerNr")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added reviewer."),
        ApiResponse(code = 401, message = "Not authorized to add reviewer!"),
        ApiResponse(code = 403, message = "Add reviewer forbidden.")
    ])
    @PostMapping("/{reviewerNr}")
    fun addReviewer(@ApiParam(name = "reviewerNr", type = "Long", value = "The reviewerNr of the reviewer being added", required = true)
                    @PathVariable reviewerNr:Long)

    @ApiOperation(value = "Delete Reviewer with reviewerNr")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully deleted reviewer."),
        ApiResponse(code = 401, message = "Not authorized to delete reviewer!"),
        ApiResponse(code = 403, message = "Delete reviewer forbidden."),
        ApiResponse(code = 404, message = "Reviewer not found.")
    ])
    @DeleteMapping("/{reviewerNr}")
    fun deleteReviewer(@ApiParam(name = "reviewerNr", type = "Long", value = "The reviewerNr of the reviewer being deleted", required = true)
                       @PathVariable reviewerNr: Long)

    @ApiOperation(value = "Edit Reviewer with reviewerNr")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully edit reviewer."),
        ApiResponse(code = 401, message = "Not authorized to edit reviewer!"),
        ApiResponse(code = 403, message = "Edit reviewer forbidden."),
        ApiResponse(code = 404, message = "Reviewer not found.")
    ])
    @PutMapping("{reviewerNr}")
    fun editReviewer(@ApiParam(name = "reviewerNr", type = "Long", value = "The reviewerNr of the reviewer being deleted", required = true)
                     @PathVariable reviewerNr: Long)

    /* panel handling */
    @ApiOperation(value = "Get list of all Panels that Reviewer with reviewerNr is assigned to", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all panels from reviewer."),
        ApiResponse(code = 401, message = "Not authorized to get panels from reviewer!"),
        ApiResponse(code = 403, message = "Get all panels from reviewer forbidden."),
        ApiResponse(code = 404, message = "Reviewer not found.")
    ])
    @GetMapping("/{reviewerNr}/panels")
    fun getPanels(@ApiParam(name = "reviewerNr", type = "Long", value = "The reviewerNr of the reviewer to get the panels from", required = true)
                  @PathVariable reviewerNr: Long):List<PanelDTO>

    @ApiOperation(value = "Get panel by id in reviewer with a given reviewerNr", response = PanelDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved panel from reviewer."),
        ApiResponse(code = 401, message = "Not authorized to get panel from reviewer!"),
        ApiResponse(code = 403, message = "Get panel from reviewer forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @GetMapping("/{reviewerNr}/panels/{panelid}")
    fun getOnePanel(@ApiParam(name = "reviewerNr", type = "Long", value = "The reviewerNr of the reviewer to get the panel from", required = true)
                    @PathVariable reviewerNr: Long, @ApiParam(name = "panelId", type = "Long", value = "The panelId of the panel being retrieved", required = true)
                    @PathVariable panelId:Long): PanelDTO

    /* reviews handling */
    @ApiOperation(value = "Get list of all Reviews that Reviewer with reviewerNr has made", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all reviews from reviewer."),
        ApiResponse(code = 401, message = "Not authorized to get reviews from reviewer!"),
        ApiResponse(code = 403, message = "Get all reviews from reviewer forbidden."),
        ApiResponse(code = 404, message = "Reviewer not found.")
    ])
    @GetMapping("/{reviewerNr}/reviews")
    fun getReviews(@ApiParam(name = "reviewerNr", type = "Long", value = "The reviewerNr of the reviewer to get the reviews from", required = true)
                   @PathVariable reviewerNr: Long):List<ReviewDTO>

    @ApiOperation(value = "Get review by id in reviewer with a given reviewerNr", response = ReviewDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved review from reviewer."),
        ApiResponse(code = 401, message = "Not authorized to get review from reviewer!"),
        ApiResponse(code = 403, message = "Get review from reviewer forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @GetMapping("/{reviewerNr}/reviews/{reviewId}")
    fun getOneReview(@ApiParam(name = "reviewerNr", type = "Long", value = "The reviewerNr of the reviewer to get the review from", required = true)
                     @PathVariable reviewerNr: Long, @ApiParam(name = "reviewId", type = "Long", value = "The reviewId of the review being retrieved", required = true)
                     @PathVariable reviewId:Long): ReviewDTO
}