package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*

@RequestMapping("/students")
interface StudentAPI {

    @ApiOperation(value = "Get list of all Students", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all students."),
        ApiResponse(code = 401, message = "Not authorized to get students!"),
        ApiResponse(code = 403, message = "Get all students forbidden.")
    ])
    @GetMapping("")
    fun getAll(): List<UserDTO>

    @ApiOperation(value = "Get Student by id", response = UserDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved student."),
        ApiResponse(code = 401, message = "Not authorized to get student!"),
        ApiResponse(code = 403, message = "Get student forbidden."),
        ApiResponse(code = 404, message = "Student not found.")
    ])
    @GetMapping("/{id}")
    fun getOne(@ApiParam(name = "id", type = "Long", value = "The id of the student being retrieved", required = true)
               @PathVariable id:Long): UserDTO


    @ApiOperation(value = "Delete Student with id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully deleted student."),
        ApiResponse(code = 401, message = "Not authorized to delete student!"),
        ApiResponse(code = 403, message = "Delete student forbidden."),
        ApiResponse(code = 404, message = "Student not found.")
    ])
    @DeleteMapping("/{id}")
    fun deleteStudent(@ApiParam(name = "id", type = "Long", value = "The id of the student being deleted", required = true)
                      @PathVariable id: Long)

    @ApiOperation(value = "Edit Student with id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully edited student."),
        ApiResponse(code = 401, message = "Not authorized to edit student!"),
        ApiResponse(code = 403, message = "Edit student forbidden."),
        ApiResponse(code = 404, message = "Student not found.")
    ])
    @PutMapping("{id}")
    fun editStudent(@ApiParam(name = "id", type = "Long", value = "The id of the student being edited", required = true)
                    @PathVariable id: Long,
                    @RequestBody student:UserDTO)

    /* application handling */
    @ApiOperation(value = "Get list of all Applications that Reviewer with reviewerNr has made", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all applications from student."),
        ApiResponse(code = 401, message = "Not authorized to get applications from student!"),
        ApiResponse(code = 403, message = "Get all applications from student forbidden."),
        ApiResponse(code = 404, message = "Student not found.")
    ])
    @GetMapping("/{id}/applications")
    fun getApplications(@ApiParam(name = "id", type = "Long", value = "The id of the student to get all applications from", required = true)
                        @PathVariable id: Long): MutableSet<ApplicationDTO>

    /* @ApiOperation(value = "Get application by id in student with a given id", response = ApplicationDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved application from student."),
        ApiResponse(code = 401, message = "Not authorized to get application from student!"),
        ApiResponse(code = 403, message = "Get application from student forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @GetMapping("/{id}/applications/{appId}")
    fun getOneApplication(@ApiParam(name = "id", type = "Long", value = "The id of the student to get the application from", required = true)
                          @PathVariable id: Long, @ApiParam(name = "appId", type = "Long", value = "The id of the application being retrieved", required = true)
                          @PathVariable appId:Long): ApplicationDTO */

    /* CV handling */
    @ApiOperation(value = "Get CV of student with a given id", response = CVDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved CV from student."),
        ApiResponse(code = 401, message = "Not authorized to get CV from student!"),
        ApiResponse(code = 403, message = "Get CV from student forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @GetMapping("/{id}/cv")
    fun getCV(@ApiParam(name = "id", type = "Long", value = "The id of the student to get the CV from", required = true)
              @PathVariable id:Long): CVDTO

    @ApiOperation(value = "Get CVItem with given id", response = CVDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved CVItem from CV."),
        ApiResponse(code = 401, message = "Not authorized to get CVItem from CV!"),
        ApiResponse(code = 403, message = "Get CVItem from CV forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @GetMapping("/{id}/cv/{cvId}")
    fun getCVItem(@ApiParam(name = "id", type = "Long", value = "The id of the student to get the CV from", required = true)
                  @PathVariable id:Long, @ApiParam(name = "cvId", type = "Long", value = "id of the CVItem", required = true)
                    @PathVariable cvId:Long) : CVItemDTO

    @ApiOperation(value = "Add CVItem with given name to CV of student with a given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added CVItem to CV from student."),
        ApiResponse(code = 401, message = "Not authorized to add CVItem to CV from student!"),
        ApiResponse(code = 403, message = "Add CVItem to CV from student forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @PostMapping("/{id}/cv")
    fun addCVItem(@ApiParam(name = "id", type = "Long", value = "The cvId of the student to delete the CVItem from", required = true)
                  @PathVariable id:Long,
                  @RequestBody cvItem: CVItemDTO)

    @ApiOperation(value = "Edit CVItem with given name from CV of student with a given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully edited CVItem from CV from student."),
        ApiResponse(code = 401, message = "Not authorized to edit CVItem from CV from student!"),
        ApiResponse(code = 403, message = "Edit CVItem to CV from student forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @PutMapping("/{id}/cv")
    fun editCVItem(@ApiParam(name = "id", type = "Long", value = "The id of the student to delete the CVItem from", required = true)
                   @PathVariable id:Long, @RequestBody cvItem: CVItemDTO)

    @ApiOperation(value = "Delete CVItem with given name from CV of student with a given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully deleted CVItem from CV from student."),
        ApiResponse(code = 401, message = "Not authorized to delete CVItem from CV from student!"),
        ApiResponse(code = 403, message = "Delete CVItem to CV from student forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @DeleteMapping("/{id}/cv/{cvId}")
    fun deleteCVItem(@ApiParam(name = "id", type = "Long", value = "The id of the student to delete the CVItem from", required = true)
                     @PathVariable id:Long,
                     @PathVariable cvId: Long)

}