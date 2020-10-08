package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RequestMapping("/panels")
interface PanelAPI {

    @GetMapping
    fun getAll():List<PanelDTO>

    @GetMapping("/{id}")
    fun getOne(@PathVariable id:Long):PanelDTO

    @PutMapping("/{id}")
    fun editPanel(@PathVariable id:Long)

    @DeleteMapping("/{id}")
    fun deletePanel(@PathVariable id:Long)

    /* Reviewer handling */
    @GetMapping("/{id}/reviewers")
    fun getReviewers(@PathVariable id: Long): List<ReviewerDTO>

    @PostMapping("/{id}/reviewers/{reviewerId}")
    fun addReviewerToPanel(@PathVariable id:Long, @PathVariable reviewerId: Long)

    @DeleteMapping("/{id}/reviewers/{reviewerId}")
    fun deleteReviewerFromPanel(@PathVariable id: Long, @PathVariable reviewerId:Long)

    @ApiOperation(value = "Get panel assigned to a grant call with given title", response = PanelDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved Panel."),
        ApiResponse(code = 401, message = "Not authorized to get Panel!"),
        ApiResponse(code = 403, message = "Get Panel forbidden."),
        ApiResponse(code = 404, message = "Panel not found.")
    ])
    @GetMapping("/call/{callTitle}")
    fun getPanelFromGrantCall(@ApiParam(name = "callTitle", type = "Long", value = "The title of the grant call to get the panel from", required = true)
            @PathVariable callTitle: Long):PanelDTO

    @ApiOperation(value = "Assign a panel to a grant call with a given title", response = PanelDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved Panel."),
        ApiResponse(code = 401, message = "Not authorized to get Panel!"),
        ApiResponse(code = 403, message = "Get Panel forbidden."),
        ApiResponse(code = 404, message = "Grant Call not found.")
    ])
    @PostMapping("/{id}/call/{callTitle}")
    fun addPanel(@ApiParam(name = "id", type = "Long", value = "The id of the panel to be created", required = true)
            @PathVariable id: Long,
            @ApiParam(name = "callTitle", type = "Long", value = "The id of the review to be created", required = true)
            @PathVariable panelId: Long)
}