package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.CVRequirementDAO
import pt.unl.fct.di.pt.firstdemo.services.CVRequirementService

@RestController
class CVRequirementController(val requirements: CVRequirementService): CVRequirementAPI {
    override fun getAll() = requirements.getAll().map { CVRequirementDTO(it) }

    override fun getOne(name: String) = CVRequirementDTO(requirements.getOne(name))

    override fun addRequirement(requirement: CVRequirementDTO) = requirements.addRequirement(CVRequirementDAO(requirement))

    override fun deleteRequirement(name: String) = requirements.deleteRequirement(name)

    override fun editRequirement(name: String, requirement: CVRequirementDTO) = requirements.editRequirement(name, CVRequirementDAO(requirement))
}