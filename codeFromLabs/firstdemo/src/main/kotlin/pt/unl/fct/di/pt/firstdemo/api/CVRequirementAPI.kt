package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*

@Api(value="Grant Management - Applications API",
        description="Operation management of CVRequirement")

@RequestMapping("/cvrequirements")
interface CVRequirementAPI {

    @ApiOperation(value = "Get list of all CVRequirements", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all CVRequirements."),
        ApiResponse(code = 401, message = "Not authorized to get CVRequirements!"),
        ApiResponse(code = 403, message = "Get all CVRequirements forbidden.")
    ])
    @GetMapping("")
    fun getAll(): List<CVRequirementDTO>

    @ApiOperation(value = "Get CVRequirement by id", response = CVRequirementDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved CVRequirement."),
        ApiResponse(code = 401, message = "Not authorized to get CVRequirement!"),
        ApiResponse(code = 403, message = "Get CVRequirement forbidden."),
        ApiResponse(code = 404, message = "CVRequirement not found.")
    ])
    @GetMapping("/{id}")
    fun getOne(@ApiParam(name = "id", type = "String", value = "The id of the CVRequirement to get", required = true)
               @PathVariable id: Long): CVRequirementDTO

    @ApiOperation(value = "Add CVRequirement by id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added CVRequirement."),
        ApiResponse(code = 401, message = "Not authorized to add CVRequirement!"),
        ApiResponse(code = 403, message = "Add CVRequirement forbidden.")
    ])
    @PostMapping("")
    fun addRequirement(@RequestBody requirement: CVRequirementDTO)

    @ApiOperation(value = "Delete CVRequirement by id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully deleted CVRequirement."),
        ApiResponse(code = 401, message = "Not authorized to delete CVRequirement!"),
        ApiResponse(code = 403, message = "Delete CVRequirement forbidden."),
        ApiResponse(code = 404, message = "CVRequirement not found.")
    ])
    @DeleteMapping("/{id}")
    fun deleteRequirement(@ApiParam(name = "id", type = "String", value = "The id of the CVRequirement to delete", required = true)
                          @PathVariable id: Long)

    @ApiOperation(value = "Edit CVRequirement by id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully edited CVRequirement."),
        ApiResponse(code = 401, message = "Not authorized to edit CVRequirement!"),
        ApiResponse(code = 403, message = "Edit CVRequirement forbidden."),
        ApiResponse(code = 404, message = "CVRequirement not found.")
    ])
    @PutMapping("{id}")
    fun editRequirement(@ApiParam(name = "id", type = "String", value = "The id of the CVRequirement to edit", required = true)
                        @PathVariable id: Long, @RequestBody requirement: CVRequirementDTO)
}