package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*

@RequestMapping("/cv_requirements")
interface CVRequirementAPI {
    @GetMapping("")
    fun getAll(): List<CVRequirementDTO>

    @GetMapping("/{name}")
    fun getOne(@PathVariable name: String): CVRequirementDTO

    @PostMapping("")
    fun addRequirement()

    @DeleteMapping("/{name}")
    fun deleteRequirement(@PathVariable name: String)

    @PutMapping("{name}")
    fun editRequirement(@PathVariable name: String)
}