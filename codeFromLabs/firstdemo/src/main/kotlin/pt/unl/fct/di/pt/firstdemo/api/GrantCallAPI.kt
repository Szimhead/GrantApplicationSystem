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
    fun getAllApplicationsFromGrantCall(@PathVariable title: String): List<ApplicationDTO>

    @PostMapping("/{title}/applications")
    fun addApplication(@PathVariable title: String, @PathVariable id: Long)

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

    @ApiOperation(value = "Assign a panel to a grant call with a given title", response = PanelDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved Panel."),
        ApiResponse(code = 401, message = "Not authorized to get Panel!"),
        ApiResponse(code = 403, message = "Get Panel forbidden."),
        ApiResponse(code = 404, message = "Grant Call not found.")
    ])
    @PostMapping("/{title}/panel")
    fun addPanel(@ApiParam(name = "title", type = "String", value = "The title of the grant call to assign the panel to", required = true)
                 @PathVariable title: String)

    @GetMapping("/{title}/panel/reviewers")
    fun getReviewers(@PathVariable title: String): List<UserDTO>

    @PostMapping("/{title}/panel/reviewers/{reviewerId}")
    fun addReviewerToPanel(@PathVariable title: String, @PathVariable reviewerId: Long)

    @DeleteMapping("/{title}/panel/reviewers/{reviewerId}")
    fun deleteReviewerFromPanel(@PathVariable title: String, @PathVariable reviewerId:Long)

    /* data item handling */
    @GetMapping("/{title}/data_items")
    fun getAllDataItems(@PathVariable title: String): List<DataItemDTO>

    @GetMapping("/{title}/{name}")
    fun getOneDataItem(@PathVariable title: String, @PathVariable name: String): DataItemDTO

    @PostMapping("/{title}")
    fun addDataItem(@PathVariable title: String)

    @DeleteMapping("/{title}/{name}")
    fun deleteDataItem(@PathVariable title: String, @PathVariable name: String)

    @PutMapping("/{title}{name}")
    fun editDataItem(@PathVariable title: String, @PathVariable name: String)

}