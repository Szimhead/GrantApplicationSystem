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
    fun getAll():Set<GrantCallDTO>

    @ApiOperation(value = "Get list of all open Grant Calls." +
            " Grant calls are open if now() is between their start and end times", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all open Grant Calls."),
        ApiResponse(code = 401, message = "Not authorized to get open Grant Calls!"),
        ApiResponse(code = 403, message = "Get all open Grant Calls forbidden.")
    ])
    @GetMapping("/open")
    fun getAllOpen():List<GrantCallDTO>

    @ApiOperation(value = "Get Grant Call by id", response = GrantCallDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved Grant Call."),
        ApiResponse(code = 401, message = "Not authorized to get Grant Call!"),
        ApiResponse(code = 403, message = "Get Grant Call forbidden."),
        ApiResponse(code = 404, message = "Grant Call not found.")
    ])
    @GetMapping("/{id}") //assuming id is unique
    fun getOne(@ApiParam(name = "id", type = "Long", value = "The id of the grant call", required = true)
            @PathVariable id: Long):GrantCallDTO

   /* @ApiOperation(value = "Add new grant call")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added Grant Call."),
        ApiResponse(code = 401, message = "Not authorized to add Grant Call!"),
        ApiResponse(code = 403, message = "Add Grant Call forbidden.")
    ])
    @PostMapping("")
    fun addCall(@RequestBody call: GrantCallDTO) */

    @ApiOperation(value = "Edit Grant Call with given id", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully edited grant call."),
        ApiResponse(code = 401, message = "Not authorized to edit grant call!"),
        ApiResponse(code = 403, message = "Edit grant call forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @PutMapping("/{id}")
    fun editCall(@ApiParam(name = "id", type = "Long", value = "The id of the grant call to edit", required = true)
                 @PathVariable id: Long, @RequestBody call: GrantCallDTO)

    @ApiOperation(value = "Delete Grant Call with given id", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully deleted grant call."),
        ApiResponse(code = 401, message = "Not authorized to delete grant call!"),
        ApiResponse(code = 403, message = "Delete grant call forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @DeleteMapping("/{id}")
    fun deleteCall(@ApiParam(name = "id", type = "Long", value = "The id of the grant call to delete", required = true)
                   @PathVariable id: Long)


    /* Application handling */
    @ApiOperation(value = "Get list of all applications in grant call with a given id", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all applications from grant call."),
        ApiResponse(code = 401, message = "Not authorized to get applications from grant call!"),
        ApiResponse(code = 403, message = "Get all applications from grant call forbidden."),
        ApiResponse(code = 404, message = "Grant Call not found.")
    ])
    @GetMapping("/{id}/applications")
    fun getAllApplicationsFromGrantCall(@ApiParam(name = "id", type = "Long", value = "The id of the grant call to get the applications from", required = true)
                                        @PathVariable id: Long): Set<ApplicationDTO>

    @ApiOperation(value = "Add an application to a grant call with a given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added application to grant call."),
        ApiResponse(code = 401, message = "Not authorized to add application to grant call!"),
        ApiResponse(code = 403, message = "Add application to grant call forbidden."),
        ApiResponse(code = 404, message = "Grant call not found.")
    ])
    @PostMapping("/{id}/applications")
    fun addApplication(@ApiParam(name = "id", type = "Long", value = "The id of the grant call to add the application to", required = true)
                       @PathVariable id: Long, @RequestBody app: ApplicationDTO)

    @ApiOperation(value = "Delete Application by id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully delete application."),
        ApiResponse(code = 401, message = "Not authorized to delete application!"),
        ApiResponse(code = 403, message = "Delete application forbidden."),
        ApiResponse(code = 404, message = "Application not found.")
    ])
    @DeleteMapping("/{id}/applications/{appId}")
    fun deleteApplication( @ApiParam(name = "id", type = "Long", value = "The id of the grant call", required = true)
                           @PathVariable id: Long, @ApiParam(name = "appId", type = "Long", value = "The id of the application to delete", required = true)
                           @PathVariable appId: Long)
    /* Panel handling */

    @ApiOperation(value = "Get panel assigned to a grant call with given id", response = PanelDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved Panel."),
        ApiResponse(code = 401, message = "Not authorized to get Panel!"),
        ApiResponse(code = 403, message = "Get Panel forbidden."),
        ApiResponse(code = 404, message = "Panel not found.")
    ])
    @GetMapping("/{id}/panel")
    fun getPanelFromGrantCall(@ApiParam(name = "id", type = "Long", value = "The id of the grant call to get the panel from", required = true)
                              @PathVariable id: Long):PanelDTO


    @ApiOperation(value = "Get all reviewers in the panel assigned to a grant call with given id", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved reviewers."),
        ApiResponse(code = 401, message = "Not authorized to get reviewers!"),
        ApiResponse(code = 403, message = "Get reviewers forbidden."),
        ApiResponse(code = 404, message = "Reviewers not found.")
    ])
    @GetMapping("/{id}/panel/reviewers")
    fun getReviewers(@ApiParam(name = "id", type = "Long", value = "The id of the grant call to get the assigned reviewers from", required = true)
                     @PathVariable id: Long): List<UserDTO>

    @ApiOperation(value = "Add panel chair to the panel assigned to a grant call with given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added reviewer."),
        ApiResponse(code = 401, message = "Not authorized to add reviewer!"),
        ApiResponse(code = 403, message = "Add reviewer forbidden.")
    ])
    @PostMapping("/{id}/panel/reviewers/{reviewerId}")
    fun addPanelChair(@ApiParam(name = "id", type = "Long", value = "The id of the grant call to add a reviewer to", required = true)
                           @PathVariable id: Long, @ApiParam(name = "reviewerId", type = "Long", value = "The id of the reviewer to add", required = true)
                           @PathVariable reviewerId: Long)

    @ApiOperation(value = "Add reviewer to the panel assigned to a grant call with given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added reviewer."),
        ApiResponse(code = 401, message = "Not authorized to add reviewer!"),
        ApiResponse(code = 403, message = "Add reviewer forbidden.")
    ])
    @PostMapping("/{id}/panel/reviewers/{reviewerId}")
    fun addReviewerToPanel(@ApiParam(name = "id", type = "Long", value = "The id of the grant call to add a reviewer to", required = true)
                           @PathVariable id: Long, @ApiParam(name = "reviewerId", type = "Long", value = "The id of the reviewer to add", required = true)
                           @PathVariable reviewerId: Long)

    @ApiOperation(value = "Remove a reviewer from the panel assigned to a grant call with given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully removed reviewer."),
        ApiResponse(code = 401, message = "Not authorized to remove reviewer!"),
        ApiResponse(code = 403, message = "Remove reviewer forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @DeleteMapping("/{id}/panel/reviewers/{reviewerId}")
    fun deleteReviewerFromPanel(@ApiParam(name = "id", type = "Long", value = "The id of the grant call to delete a reviewer from", required = true)
                                @PathVariable id: Long, @ApiParam(name = "reviewerId", type = "Long", value = "The id of the reviewer being deleted", required = true)
                                @PathVariable reviewerId: Long)

    /* data item handling */
    @ApiOperation(value = "Get list of all Data Items in Grant Call with a given id", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all data items from grant call."),
        ApiResponse(code = 401, message = "Not authorized to get data items from grant call!"),
        ApiResponse(code = 403, message = "Get all data items from grant call forbidden."),
        ApiResponse(code = 404, message = "Grant Call not found.")
    ])
    @GetMapping("/{id}/dataitems")
    fun getAllDataItems(@ApiParam(name = "id", type = "Long", value = "The id of the grant call to get the data items from", required = true)
                        @PathVariable id: Long): List<DataItemDTO>

    @ApiOperation(value = "Get data item by name in grant call with a given id", response = DataItemDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved data item from grant call."),
        ApiResponse(code = 401, message = "Not authorized to get data item from grant call!"),
        ApiResponse(code = 403, message = "Get data item from grant call forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @GetMapping("/{id}/dataitems/{dataItemId}")
    fun getOneDataItem(@ApiParam(name = "id", type = "Long", value = "The id of the grant call to get the data item from", required = true)
                       @PathVariable id: Long, @ApiParam(name = "dataItemId", type = "Long", value = "The id of the data item to get", required = true)
                       @PathVariable dataItemId: Long): DataItemDTO

    @ApiOperation(value = "Add data item to grant call with a given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added data item to grant call."),
        ApiResponse(code = 401, message = "Not authorized to add data item to grant call!"),
        ApiResponse(code = 403, message = "Add data item to grant call forbidden."),
        ApiResponse(code = 404, message = "Grant call not found.")
    ])
    @PostMapping("/{id}/dataitems")
    fun addDataItem(@ApiParam(name = "id", type = "Long", value = "The id of the grant call to add the data item to", required = true)
                    @PathVariable id: Long, @RequestBody dataItem: DataItemDTO)

    @ApiOperation(value = "Delete data item from grant call with a given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully deleted data item from grant call."),
        ApiResponse(code = 401, message = "Not authorized to delete data item from grant call!"),
        ApiResponse(code = 403, message = "Delete data item from grant call forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @DeleteMapping("/{id}/dataitems/{dataItemId}")
    fun deleteDataItem(@ApiParam(name = "id", type = "Long", value = "The id of the grant call to delete the data item from", required = true)
                       @PathVariable id: Long, @ApiParam(name = "dataItemId", type = "Long", value = "The id of the data item being deleted", required = true)
                       @PathVariable dataItemId: Long)

    @ApiOperation(value = "Edit data item from grant call with a given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully edited data item from grant call."),
        ApiResponse(code = 401, message = "Not authorized to edit data item from grant call!"),
        ApiResponse(code = 403, message = "Edit data item from grant call forbidden."),
        ApiResponse(code = 404, message = "Not found.")
    ])
    @PutMapping("/{id}/dataitems/{dataItemId}")
    fun editDataItem(@ApiParam(name = "id", type = "Long", value = "The id of the grant call to edit the data item from", required = true)
                     @PathVariable id: Long, @ApiParam(name = "dataItemId", type = "Long", value = "The id of the data item being edited", required = true)
                     @PathVariable dataItemId: Long, @RequestBody dataItem: DataItemDTO)

}