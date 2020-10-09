package pt.unl.fct.di.pt.grantManagementApplication.api

import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*

@Api(value="Grant Management - CV Requirements API",
        description="Operation management of CVRequirements")
@RequestMapping("/cvrequirements")
interface CVRequirementsAPI {

    @ApiOperation(value = "Get list of all CVRequirements", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all CVRequirements."),
        ApiResponse(code = 401, message = "Not authorized to get CVRequirements!"),
        ApiResponse(code = 403, message = "Get all CVRequirements forbidden.")
    ])
    @GetMapping("")
    fun getAll(): List<CVRequirementDTO>

    @ApiOperation(value = "Get CVRequirement by name", response = CVRequirementDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved CVRequirement."),
        ApiResponse(code = 401, message = "Not authorized to get CVRequirement!"),
        ApiResponse(code = 403, message = "Get CVRequirement forbidden."),
        ApiResponse(code = 404, message = "CVRequirement not found.")
    ])
    @GetMapping("/{name}")
    fun getOne(@ApiParam(name = "name", type = "String", value = "The name of the CVRequirement to get", required = true)
               @PathVariable name: String): CVRequirementDTO

    @ApiOperation(value = "Add CVRequirement by name")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added CVRequirement."),
        ApiResponse(code = 401, message = "Not authorized to add CVRequirement!"),
        ApiResponse(code = 403, message = "Add CVRequirement forbidden.")
    ])
    @PostMapping("/{name}")
    fun addRequirement(@ApiParam(name = "name", type = "String", value = "The name of the CVRequirement being added", required = true)
                       @PathVariable name: String)

    @ApiOperation(value = "Delete CVRequirement by name")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully deleted CVRequirement."),
        ApiResponse(code = 401, message = "Not authorized to delete CVRequirement!"),
        ApiResponse(code = 403, message = "Delete CVRequirement forbidden."),
        ApiResponse(code = 404, message = "CVRequirement not found.")
    ])
    @DeleteMapping("/{name}")
    fun deleteRequirement(@ApiParam(name = "name", type = "String", value = "The name of the CVRequirement to delete", required = true)
                          @PathVariable name: String)

    @ApiOperation(value = "Edit CVRequirement by name")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully edited CVRequirement."),
        ApiResponse(code = 401, message = "Not authorized to edit CVRequirement!"),
        ApiResponse(code = 403, message = "Edit CVRequirement forbidden."),
        ApiResponse(code = 404, message = "CVRequirement not found.")
    ])
    @PutMapping("{name}")
    fun editRequirement(@ApiParam(name = "name", type = "String", value = "The name of the CVRequirement to edit", required = true)
                        @PathVariable name: String)
}