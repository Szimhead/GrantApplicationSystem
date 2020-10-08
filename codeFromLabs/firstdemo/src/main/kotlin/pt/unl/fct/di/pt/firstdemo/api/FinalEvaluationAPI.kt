package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*

@RequestMapping("/finals")
interface FinalEvaluationAPI {

    @ApiOperation(value = "Get list of all Final Evaluations", response = Iterable::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved all Final Evaluations."),
        ApiResponse(code = 401, message = "Not authorized to get Final Evaluations!"),
        ApiResponse(code = 403, message = "Get all Final Evaluations forbidden.")
    ])
    @GetMapping
    fun getAll(): List<FinalDTO>

    @ApiOperation(value = "Get Final Evaluation by id", response = FinalDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved Final Evaluation."),
        ApiResponse(code = 401, message = "Not authorized to get Final Evaluation!"),
        ApiResponse(code = 403, message = "Get Final Evaluation forbidden."),
        ApiResponse(code = 404, message = "Final Evaluation not found.")
    ])
    @GetMapping("/{id}")
    fun getOne(@ApiParam(name = "id", type = "Long", value = "The id of the final evaluation", required = true)
            @PathVariable id:Long):FinalDTO

    @PostMapping()
    fun addFinal(@RequestParam final:Long)

    @ApiOperation(value = "Edit Final Evaluation with given id", response = Iterable::class)
    @PutMapping("/{id}")
    fun editFinal(@PathVariable id:Long)

    @ApiOperation(value = "Delete Final Evaluation with given id", response = Iterable::class)
    @DeleteMapping("{id}")
    fun deleteFinal(@PathVariable id:Long)

    @ApiOperation(value = "Get final evaluation of application with a given id", response = FinalDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved final evaluation from application."),
        ApiResponse(code = 401, message = "Not authorized to get final evaluation!"),
        ApiResponse(code = 403, message = "Get final evaluation forbidden."),
        ApiResponse(code = 404, message = "Application not found.")
    ])
    @GetMapping("/application/{id}")
    fun getFinalFromApplication(@ApiParam(name = "id",
            type = "Long",
            value = "The id of the application to get the evaluation from",
            required = true) @PathVariable id: Long): FinalDTO   // default final status should be "pending" or something

}