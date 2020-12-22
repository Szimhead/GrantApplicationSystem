package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*

@Api(value="Grant Management - Data Item API",
        description="Operation management of Data Item")

@RequestMapping("/dataitems")
interface DataItemAPI {
    
    @ApiOperation(value = "Get list of all dataItems", response = Set::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all dataItems."),
        ApiResponse(code = 401, message = "Not authorized to get dataItems!"),
        ApiResponse(code = 403, message = "Get all dataItems forbidden.")
    ])
    @GetMapping("")
    fun getAll(): List<DataItemDTO>

    @ApiOperation(value = "Get dataItem by id", response = DataItemDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved dataItem."),
        ApiResponse(code = 401, message = "Not authorized to get dataItem!"),
        ApiResponse(code = 403, message = "Get dataItem forbidden."),
        ApiResponse(code = 404, message = "dataItem not found.")
    ])
    @GetMapping("/{id}")
    fun getOne(@ApiParam(name = "id", type = "String", value = "The id of the dataItem to get", required = true)
               @PathVariable id: Long): DataItemDTO

    @ApiOperation(value = "Add dataItem by id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added dataItem."),
        ApiResponse(code = 401, message = "Not authorized to add dataItem!"),
        ApiResponse(code = 403, message = "Add dataItem forbidden.")
    ])
    @PostMapping("")
    fun addDataItem(@RequestBody dataItem: DataItemDTO)
}