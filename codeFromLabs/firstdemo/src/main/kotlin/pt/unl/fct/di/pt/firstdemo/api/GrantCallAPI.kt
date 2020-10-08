package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*

@RequestMapping("/calls")
interface GrantCallAPI {

    @ApiOperation(value = "Get list of all Grant Calls", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all Grant Calls."),
        ApiResponse(code = 401, message = "Not authorized to get Grant Calls!"),
        ApiResponse(code = 403, message = "Get all Grant Calls forbidden.")
    ])
    @GetMapping("")
    fun getAll():List<GrantCallDTO>

    @ApiOperation(value = "Get list of all open Grant Calls." +
            " Grant calls are open if now() is between their start and end times", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all open Grant Calls."),
        ApiResponse(code = 401, message = "Not authorized to get open Grant Calls!"),
        ApiResponse(code = 403, message = "Get all open Grant Calls forbidden.")
    ])
    @GetMapping("/open")
    fun getAllOpen():List<GrantCallDTO>

    @ApiOperation(value = "Get Grant Call by title", response = GrantCallDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved Grant Call."),
        ApiResponse(code = 401, message = "Not authorized to get Grant Call!"),
        ApiResponse(code = 403, message = "Get Grant Call forbidden."),
        ApiResponse(code = 404, message = "Grant Call not found.")
    ])
    @GetMapping("/{title}") //assuming title is unique
    fun getOne(@ApiParam(name = "title", type = "Long", value = "The title of the grant call", required = true)
            @PathVariable title:String):GrantCallDTO

    @PostMapping("")
    fun addCall(/*TODO*/)

    @ApiOperation(value = "Edit Grant Call with given id", response = Iterable::class)
    @PutMapping("/{title}")
    fun editCall(@PathVariable title:String)

    @ApiOperation(value = "Delete Grant Call with given id", response = Iterable::class)
    @DeleteMapping("/{title}")
    fun deleteCall(@PathVariable title: String)



    /* Application handling */
    @GetMapping("/{title}/applications")
    fun getCallApplications(@PathVariable title: String): List<ApplicationDTO>

    @GetMapping("/{title}/applications/{id}")
    fun getOneCallApplication(@PathVariable title: String, @PathVariable id: Long): ApplicationDTO

    @PostMapping("/{title}/applications/{id}")
    fun addApplication(@PathVariable title: String, @PathVariable id: Long)

}