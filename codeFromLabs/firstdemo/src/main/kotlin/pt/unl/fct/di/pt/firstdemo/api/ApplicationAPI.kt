package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*
import kotlin.collections.*
import java.util.*

@Api(value="Grant Management - Applications API",
        description="Operation management of Applications")

@RequestMapping("/applications")
interface ApplicationAPI {

    @ApiOperation(value = "Get list of all Applications", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all applications."),
        ApiResponse(code = 401, message = "Not authorized to get applications!"),
        ApiResponse(code = 403, message = "Get all applications forbidden.")
    ])
    @GetMapping("")
    fun getAll():List<ApplicationDTO>

    @ApiOperation(value = "Get Application by id", response = ApplicationDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved application."),
        ApiResponse(code = 401, message = "Not authorized to get application!"),
        ApiResponse(code = 403, message = "Get application forbidden."),
        ApiResponse(code = 404, message = "Application not found.")
    ])
    @GetMapping("/{id}")
    fun getOne( @ApiParam(name = "id", type = "Long", value = "The id of the application", required = true)
                @PathVariable id:Long): ApplicationDTO

}
