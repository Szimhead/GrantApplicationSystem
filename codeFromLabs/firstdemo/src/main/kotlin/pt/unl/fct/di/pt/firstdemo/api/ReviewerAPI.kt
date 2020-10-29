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

    @ApiOperation(value = "Get Reviewer by id", response = UserDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved reviewer."),
        ApiResponse(code = 401, message = "Not authorized to get reviewer!"),
        ApiResponse(code = 403, message = "Get reviewer forbidden."),
        ApiResponse(code = 404, message = "Reviewer not found.")
    ])
    @GetMapping("/{id}")
    fun getOne(@ApiParam(name = "id", type = "Long", value = "The id of the reviewer being retrieved", required = true)
               @PathVariable id:Long): UserDTO

    @ApiOperation(value = "Delete Reviewer with id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully deleted reviewer."),
        ApiResponse(code = 401, message = "Not authorized to delete reviewer!"),
        ApiResponse(code = 403, message = "Delete reviewer forbidden."),
        ApiResponse(code = 404, message = "Reviewer not found.")
    ])
    @DeleteMapping("/{id}")
    fun deleteReviewer(@ApiParam(name = "id", type = "Long", value = "The id of the reviewer being deleted", required = true)
                       @PathVariable id: Long)

    @ApiOperation(value = "Edit Reviewer with id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully edit reviewer."),
        ApiResponse(code = 401, message = "Not authorized to edit reviewer!"),
        ApiResponse(code = 403, message = "Edit reviewer forbidden."),
        ApiResponse(code = 404, message = "Reviewer not found.")
    ])
    @PutMapping("{id}")
    fun editReviewer(@ApiParam(name = "id", type = "Long", value = "The id of the reviewer being updated", required = true)
                     @PathVariable id: Long,
                     @RequestBody reviewer: UserDTO)

    /* panel handling */
    @ApiOperation(value = "Get list of all Panels that Reviewer with id is assigned to", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all panels from reviewer."),
        ApiResponse(code = 401, message = "Not authorized to get panels from reviewer!"),
        ApiResponse(code = 403, message = "Get all panels from reviewer forbidden."),
        ApiResponse(code = 404, message = "Reviewer not found.")
    ])
    @GetMapping("/{id}/panels")
    fun getPanels(@ApiParam(name = "id", type = "Long", value = "The id of the reviewer to get the panels from", required = true)
                  @PathVariable id: Long):List<PanelDTO>

    @ApiOperation(value = "Get panel by id in reviewer with a given id", response = PanelDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved panel from reviewer."),
        ApiResponse(code = 401, message = "Not authorized to get panel from reviewer!"),
        ApiResponse(code = 403, message = "Get panel from reviewer forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @GetMapping("/{id}/panels/{panelid}")
    fun getOnePanel(@ApiParam(name = "id", type = "Long", value = "The id of the reviewer to get the panel from", required = true)
                    @PathVariable id: Long, @ApiParam(name = "panelId", type = "Long", value = "The panelId of the panel being retrieved", required = true)
                    @PathVariable panelId:Long): PanelDTO

    /* reviews handling */
    @ApiOperation(value = "Get list of all Reviews that Reviewer with id has made", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all reviews from reviewer."),
        ApiResponse(code = 401, message = "Not authorized to get reviews from reviewer!"),
        ApiResponse(code = 403, message = "Get all reviews from reviewer forbidden."),
        ApiResponse(code = 404, message = "Reviewer not found.")
    ])
    @GetMapping("/{id}/reviews")
    fun getReviews(@ApiParam(name = "id", type = "Long", value = "The id of the reviewer to get the reviews from", required = true)
                   @PathVariable id: Long):List<ReviewDTO>

    @ApiOperation(value = "Get review by id in reviewer with a given id", response = ReviewDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved review from reviewer."),
        ApiResponse(code = 401, message = "Not authorized to get review from reviewer!"),
        ApiResponse(code = 403, message = "Get review from reviewer forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @GetMapping("/{id}/reviews/{reviewId}")
    fun getOneReview(@ApiParam(name = "id", type = "Long", value = "The id of the reviewer to get the review from", required = true)
                     @PathVariable id: Long, @ApiParam(name = "reviewId", type = "Long", value = "The reviewId of the review being retrieved", required = true)
                     @PathVariable reviewId:Long): ReviewDTO
}