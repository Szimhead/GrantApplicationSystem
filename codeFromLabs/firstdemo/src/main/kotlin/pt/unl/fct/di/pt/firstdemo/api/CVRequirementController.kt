package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.CVRequirementDAO
import pt.unl.fct.di.pt.firstdemo.services.CVRequirementService

@RestController
class CVRequirementController(val reqs: CVRequirementService): CVRequirementAPI {
    override fun getAll() = reqs.getAll().map { CVRequirementDTO(it) }

    override fun getOne(id: Long) = CVRequirementDTO(reqs.getOne(id))

    override fun addRequirement(requirement: CVRequirementDTO) = reqs.addRequirement(CVRequirementDAO(requirement))

    override fun deleteRequirement(id: Long) = reqs.deleteRequirement(reqs.getOne(id))

    override fun editRequirement(id: Long, requirement: CVRequirementDTO) = reqs.editRequirement(reqs.getOne(id), CVRequirementDAO(requirement))
}