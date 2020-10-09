package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.CVRequirementService

@RestController
class CVRequirementController(val requirements: CVRequirementService): CVRequirementAPI {
    override fun getAll() = requirements.getAll()

    override fun getOne(name: String) = requirements.getOne(name)

    override fun addRequirement(name: String) = requirements.addRequirement(name)

    override fun deleteRequirement(name: String) = requirements.deleteRequirement(name)

    override fun editRequirement(name: String) = requirements.editRequirement(name)
}