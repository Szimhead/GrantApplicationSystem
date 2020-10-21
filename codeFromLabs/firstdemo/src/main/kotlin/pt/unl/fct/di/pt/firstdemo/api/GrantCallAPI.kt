package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.apache.catalina.User
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
    fun getOne(@ApiParam(name = "title", type = "String", value = "The title of the grant call", required = true)
            @PathVariable title:String):GrantCallDTO

    @ApiOperation(value = "Add call with given title")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added Grant Call."),
        ApiResponse(code = 401, message = "Not authorized to add Grant Call!"),
        ApiResponse(code = 403, message = "Add Grant Call forbidden.")
    ])
    @PostMapping("/{title}")
    fun addCall(@ApiParam(name = "title", type = "String", value = "The title of the grant call", required = true)
                @PathVariable title:String)

    @ApiOperation(value = "Edit Grant Call with given id", response = Iterable::class)
    @PutMapping("/{title}")
    fun editCall(@PathVariable title:String)

    @ApiOperation(value = "Delete Grant Call with given id", response = Iterable::class)
    @DeleteMapping("/{title}")
    fun deleteCall(@PathVariable title: String)


    /* Application handling */
    @ApiOperation(value = "Get list of all Applications in Grant Call with a given title", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all applications from grant call."),
        ApiResponse(code = 401, message = "Not authorized to get applications from grant call!"),
        ApiResponse(code = 403, message = "Get all applications from grant call forbidden."),
        ApiResponse(code = 404, message = "Grant Call not found.")
    ])
    @GetMapping("/{title}/applications")
    fun getAllApplicationsFromGrantCall(@ApiParam(name = "title", type = "String", value = "The title of the grant call to get the applications from", required = true)
                                        @PathVariable title: String): List<ApplicationDTO>

    @ApiOperation(value = "Add an application to an grant call with a given title")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added application to grant call."),
        ApiResponse(code = 401, message = "Not authorized to add application to grant call!"),
        ApiResponse(code = 403, message = "Add application to grant call forbidden."),
        ApiResponse(code = 404, message = "Grant call not found.")
    ])
    @PostMapping("/{title}/applications")
    fun addApplication(@ApiParam(name = "title", type = "String", value = "The title of the grant call to add the application to", required = true)
                       @PathVariable title: String, @RequestBody app: ApplicationDTO)

    /* Panel handling */

    @ApiOperation(value = "Get panel assigned to a grant call with given title", response = PanelDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved Panel."),
        ApiResponse(code = 401, message = "Not authorized to get Panel!"),
        ApiResponse(code = 403, message = "Get Panel forbidden."),
        ApiResponse(code = 404, message = "Panel not found.")
    ])
    @GetMapping("/{title}/panel")
    fun getPanelFromGrantCall(@ApiParam(name = "title", type = "String", value = "The title of the grant call to get the panel from", required = true)
                              @PathVariable title: String):PanelDTO

    /* TODO: DELETE?? */
    @ApiOperation(value = "Assign a panel to a grant call with a given title")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved Panel."),
        ApiResponse(code = 401, message = "Not authorized to get Panel!"),
        ApiResponse(code = 403, message = "Get Panel forbidden."),
        ApiResponse(code = 404, message = "Grant Call not found.")
    ])
    @PostMapping("/{title}/panel")
    fun addPanel(@ApiParam(name = "title", type = "String", value = "The title of the grant call to assign the panel to", required = true)
                 @PathVariable title: String)

    @ApiOperation(value = "Get all reviewers in the panel assigned to a grant call with given title", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved reviewers."),
        ApiResponse(code = 401, message = "Not authorized to get reviewers!"),
        ApiResponse(code = 403, message = "Get reviewers forbidden."),
        ApiResponse(code = 404, message = "Reviewers not found.")
    ])
    @GetMapping("/{title}/panel/reviewers")
    fun getReviewers(@ApiParam(name = "title", type = "String", value = "The title of the grant call to get the assigned reviewers from", required = true)
                     @PathVariable title: String): List<UserDTO>

    @ApiOperation(value = "Add reviewer to the panel assigned to a grant call with given title")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added reviewer."),
        ApiResponse(code = 401, message = "Not authorized to add reviewer!"),
        ApiResponse(code = 403, message = "Add reviewer forbidden.")
    ])
    @PostMapping("/{title}/panel/reviewers/{reviewerId}")
    fun addReviewerToPanel(@ApiParam(name = "title", type = "String", value = "The title of the grant call to add a reviewer to", required = true)
                           @PathVariable title: String, @ApiParam(name = "reviewerId", type = "Long", value = "The id of the reviewer being added", required = true)
                           @PathVariable reviewerId: Long)

    @ApiOperation(value = "Remove a reviewer from the panel assigned to a grant call with given title")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully removed reviewer."),
        ApiResponse(code = 401, message = "Not authorized to remove reviewer!"),
        ApiResponse(code = 403, message = "Remove reviewer forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @DeleteMapping("/{title}/panel/reviewers/{reviewerId}")
    fun deleteReviewerFromPanel(@ApiParam(name = "title", type = "String", value = "The title of the grant call to delete a reviewer from", required = true)
                                @PathVariable title: String, @ApiParam(name = "reviewerId", type = "Long", value = "The id of the reviewer being deleted", required = true)
                                @PathVariable reviewerId: Long)

    /* data item handling */
    @ApiOperation(value = "Get list of all Data Items in Grant Call with a given title", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all data items from grant call."),
        ApiResponse(code = 401, message = "Not authorized to get data items from grant call!"),
        ApiResponse(code = 403, message = "Get all data items from grant call forbidden."),
        ApiResponse(code = 404, message = "Grant Call not found.")
    ])
    @GetMapping("/{title}/dataitems")
    fun getAllDataItems(@ApiParam(name = "title", type = "String", value = "The title of the grant call to get the data items from", required = true)
                        @PathVariable title: String): List<DataItemDTO>

    @ApiOperation(value = "Get data item by name in grant call with a given title", response = DataItemDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved data item from grant call."),
        ApiResponse(code = 401, message = "Not authorized to get data item from grant call!"),
        ApiResponse(code = 403, message = "Get data item from grant call forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @GetMapping("/{title}/dataitems/{name}")
    fun getOneDataItem(@ApiParam(name = "title", type = "String", value = "The title of the grant call to get the data item from", required = true)
                       @PathVariable title: String, @ApiParam(name = "name", type = "String", value = "The name of the data item to get", required = true)
                       @PathVariable name: String): DataItemDTO

    @ApiOperation(value = "Add data item to grant call with a given title")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added data item to grant call."),
        ApiResponse(code = 401, message = "Not authorized to add data item to grant call!"),
        ApiResponse(code = 403, message = "Add data item to grant call forbidden."),
        ApiResponse(code = 404, message = "Grant call not found.")
    ])
    @PostMapping("/{title}/dataitems/{name}")
    fun addDataItem(@ApiParam(name = "title", type = "String", value = "The title of the grant call to add the data item to", required = true)
                    @PathVariable title: String, @ApiParam(name = "name", type = "String", value = "The name of the data item being added", required = true)
                    @PathVariable name: String)

    @ApiOperation(value = "Delete data item from grant call with a given title")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully deleted data item from grant call."),
        ApiResponse(code = 401, message = "Not authorized to delete data item from grant call!"),
        ApiResponse(code = 403, message = "Delete data item from grant call forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @DeleteMapping("/{title}/dataitems/{name}")
    fun deleteDataItem(@ApiParam(name = "title", type = "String", value = "The title of the grant call to delete the data item from", required = true)
                       @PathVariable title: String, @ApiParam(name = "name", type = "String", value = "The name of the data item being deleted", required = true)
                       @PathVariable name: String)

    @ApiOperation(value = "Edit data item from grant call with a given title")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully edited data item from grant call."),
        ApiResponse(code = 401, message = "Not authorized to edit data item from grant call!"),
        ApiResponse(code = 403, message = "Edit data item from grant call forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @PutMapping("/{title}/dataitems/{name}")
    fun editDataItem(@ApiParam(name = "title", type = "String", value = "The title of the grant call to edit the data item from", required = true)
                     @PathVariable title: String, @ApiParam(name = "name", type = "String", value = "The name of the data item being edited", required = true)
                     @PathVariable name: String)

}