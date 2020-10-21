package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*

@RequestMapping("/institutions")
interface InstitutionAPI {

    @ApiOperation(value = "Get list of all Institutions", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all institutions."),
        ApiResponse(code = 401, message = "Not authorized to get institutions!"),
        ApiResponse(code = 403, message = "Get all institutions forbidden.")
    ])
    @GetMapping
    fun getAll():List<OrganizationDTO>

    @ApiOperation(value = "Get Institution by id", response = OrganizationDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved institution."),
        ApiResponse(code = 401, message = "Not authorized to get institution!"),
        ApiResponse(code = 403, message = "Get institution forbidden."),
        ApiResponse(code = 404, message = "Institution not found.")
    ])
    @GetMapping("/{id}")
    fun getOne(@ApiParam(name = "id", type = "Long", value = "The id of the institution", required = true)
               @PathVariable id:Long): OrganizationDTO

    @ApiOperation(value = "Add a new Institution")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added institution."),
        ApiResponse(code = 401, message = "Not authorized to add institution!"),
        ApiResponse(code = 403, message = "Add institution forbidden.")
    ])
    @PostMapping("")
    fun addInstitution(@RequestBody institution: OrganizationDTO)

    @ApiOperation(value = "Delete Institution by id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully deleted institution."),
        ApiResponse(code = 401, message = "Not authorized to delete institution!"),
        ApiResponse(code = 403, message = "Delete institution forbidden."),
        ApiResponse(code = 404, message = "Institution not found.")
    ])
    @DeleteMapping("/{id}")
    fun deleteInstitution( @ApiParam(name = "id", type = "Long", value = "The id of the institution being deleted", required = true)
                           @PathVariable id: Long)


    @ApiOperation(value = "Edit Institution with given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully edit institution."),
        ApiResponse(code = 401, message = "Not authorized to edit institution!"),
        ApiResponse(code = 403, message = "Edit institution forbidden."),
        ApiResponse(code = 404, message = "Institution not found.")
    ])
    @PutMapping("/{id}")
    fun editInstitution(@ApiParam(name = "id", type = "Long", value = "The id of the institution being edited", required = true)
                        @PathVariable id: Long)


    /* student handling */
    @ApiOperation(value = "Get list of all Students in Institution with a given id", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all students from institution."),
        ApiResponse(code = 401, message = "Not authorized to get students from institution!"),
        ApiResponse(code = 403, message = "Get all students from institution forbidden."),
        ApiResponse(code = 404, message = "Institution not found.")
    ])
    @GetMapping("/{id}/students")
    fun getStudents(@ApiParam(name = "id", type = "Long", value = "The id of the institution to get all students from", required = true)
                    @PathVariable id:Long): List<UserDTO>

    @ApiOperation(value = "Get Student with studentNr in Institution with a given id", response = UserDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved student from institution."),
        ApiResponse(code = 401, message = "Not authorized to add student from institution!"),
        ApiResponse(code = 403, message = "Add student from institution forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @GetMapping("/{id}/students/{studentNr}")
    fun getOneStudent(@ApiParam(name = "id", type = "Long", value = "The id of the institution to get the student from", required = true)
                      @PathVariable id:Long, @ApiParam(name = "studentNr", type = "Long", value = "The studentNr of the student being retrieved", required = true)
                      @PathVariable studentNr:Long): UserDTO

    @ApiOperation(value = "Add new Student to Institution with a given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added student to institution."),
        ApiResponse(code = 401, message = "Not authorized to add student to institution!"),
        ApiResponse(code = 403, message = "Add student to institution forbidden.")
    ])
    @PostMapping("/{id}/students")
    fun addStudent(@ApiParam(name = "id", type = "Long", value = "The id of the institution to add the student to", required = true)
                   @PathVariable id:Long, @RequestBody student: UserDTO)

    /* reviewer handling */
    @ApiOperation(value = "Get list of all Reviewers in Institution with a given id", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all reviewers from institution."),
        ApiResponse(code = 401, message = "Not authorized to get reviewers from institution!"),
        ApiResponse(code = 403, message = "Get all reviewers from institution forbidden."),
        ApiResponse(code = 404, message = "Institution not found.")
    ])
    @GetMapping("/{id}/reviewers")
    fun getReviewers(@ApiParam(name = "id", type = "Long", value = "The id of the institution to get all reviewers from", required = true)
                     @PathVariable id:Long): List<UserDTO>

    @ApiOperation(value = "Get Reviewer with reviewerNr in Institution with a given id", response = UserDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved reviewer from institution."),
        ApiResponse(code = 401, message = "Not authorized to add reviewer from institution!"),
        ApiResponse(code = 403, message = "Add reviewer from institution forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @GetMapping("/{id}/reviewers/{reviewerNr}")
    fun getOneReviewer(@ApiParam(name = "id", type = "Long", value = "The id of the institution to get the reviewer from", required = true)
                       @PathVariable id:Long, @ApiParam(name = "reviewerNr", type = "Long", value = "The reviewerNr of the reviewer being retrieved", required = true)
                       @PathVariable reviewerNr:Long): UserDTO

    @ApiOperation(value = "Add new Reviewer to Institution with a given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added reviewer to institution."),
        ApiResponse(code = 401, message = "Not authorized to add reviewer to institution!"),
        ApiResponse(code = 403, message = "Add reviewer to institution forbidden.")
    ])
    @PostMapping("/{id}/reviewers")
    fun addReviewer(@ApiParam(name = "id", type = "Long", value = "The id of the institution to add the reviewer to", required = true)
                    @PathVariable id:Long, @RequestBody reviewer: UserDTO)

}