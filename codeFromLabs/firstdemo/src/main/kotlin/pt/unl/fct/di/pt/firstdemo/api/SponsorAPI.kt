package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*

@RequestMapping("/sponsors")
interface SponsorAPI {

    @ApiOperation(value = "Get list of all Sponsors", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all sponsors."),
        ApiResponse(code = 401, message = "Not authorized to get sponsors!"),
        ApiResponse(code = 403, message = "Get all sponsors forbidden.")
    ])
    @GetMapping
    fun getAll():List<OrganizationDTO>

    @ApiOperation(value = "Get Organization by id", response = OrganizationDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved organization."),
        ApiResponse(code = 401, message = "Not authorized to get organization!"),
        ApiResponse(code = 403, message = "Get organization forbidden."),
        ApiResponse(code = 404, message = "Organization not found.")
    ])
    @GetMapping("/{id}")
    fun getOne(@ApiParam(name = "id", type = "Long", value = "The id of the organization", required = true)
               @PathVariable id:Long): OrganizationDTO

    @ApiOperation(value = "Add Sponsor with id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added sponsor."),
        ApiResponse(code = 401, message = "Not authorized to add sponsor!"),
        ApiResponse(code = 403, message = "Add sponsor forbidden.")
    ])
    @PostMapping("/{id}")
    fun addSponsor(@ApiParam(name = "id", type = "Long", value = "The id of the sponsor being added", required = true)
                   @PathVariable reviewerNr:Long)

    @ApiOperation(value = "Delete Sponsor with reviewerNr")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully deleted sponsor."),
        ApiResponse(code = 401, message = "Not authorized to delete sponsor!"),
        ApiResponse(code = 403, message = "Delete sponsor forbidden."),
        ApiResponse(code = 404, message = "Sponsor not found.")
    ])
    @DeleteMapping("/{id}")
    fun deleteSponsor(@ApiParam(name = "id", type = "Long", value = "The id of the sponsor being deleted", required = true)
                      @PathVariable id:Long)

    @ApiOperation(value = "Edit Sponsor with reviewerNr")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully edited sponsor."),
        ApiResponse(code = 401, message = "Not authorized to edit sponsor!"),
        ApiResponse(code = 403, message = "Edit sponsor forbidden."),
        ApiResponse(code = 404, message = "Sponsor not found.")
    ])
    @PutMapping("/{id}")
    fun editSponsor(@ApiParam(name = "id", type = "Long", value = "The id of the sponsor being edited", required = true)
                    @PathVariable id:Long)

    /* grant call handling */
    @ApiOperation(value = "Get list of all Grant Calls that Sponsor with id has created", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all grant calls from sponsor."),
        ApiResponse(code = 401, message = "Not authorized to get grant calls from sponsor!"),
        ApiResponse(code = 403, message = "Get all grant calls from sponsor forbidden."),
        ApiResponse(code = 404, message = "Sponsor not found.")
    ])
    @GetMapping("/{id}/grantcalls")
    fun getGrantCalls(@ApiParam(name = "id", type = "Long", value = "The id of the sponsor to get all grant calls from", required = true)
                      @PathVariable id:Long): List<GrantCallDTO>

    @ApiOperation(value = "Add a grant call with title to the sponsor with the given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added grant call to sponsor."),
        ApiResponse(code = 401, message = "Not authorized to add grant call to sponsor!"),
        ApiResponse(code = 403, message = "Add grant call to sponsor forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @PostMapping("/{id}/grantcalls/{title}")
    fun addGrantCall(@ApiParam(name = "id", type = "Long", value = "The id of the sponsor to add the grant call to", required = true)
                     @PathVariable id:Long, @ApiParam(name = "title", type = "String", value = "The title of the grant call being created", required = true)
                     @PathVariable title: String)

}