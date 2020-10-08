package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RequestMapping("/panels")
interface PanelAPI {

    /* Reviewer handling */
    @GetMapping("/{id}/reviewers")
    fun getReviewers(@PathVariable id: Long): List<UserDTO>

    @PostMapping("/{id}/reviewers/{reviewerId}")
    fun addReviewerToPanel(@PathVariable id:Long, @PathVariable reviewerId: Long)

    @DeleteMapping("/{id}/reviewers/{reviewerId}")
    fun deleteReviewerFromPanel(@PathVariable id: Long, @PathVariable reviewerId:Long)


}