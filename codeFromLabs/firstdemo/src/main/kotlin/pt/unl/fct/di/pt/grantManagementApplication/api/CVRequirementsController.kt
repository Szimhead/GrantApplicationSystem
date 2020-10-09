package pt.unl.fct.di.pt.grantManagementApplication.api

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.grantManagementApplication.services.CVRequirementsService

@Api(value="CV Requirements", description = "Operation management of CV Requirements", tags=["CV Requirements"])
@RestController
class CVRequirementsController(val requirements: CVRequirementsService): CVRequirementsAPI {
    override fun getAll() = requirements.getAll()

    override fun getOne(name: String) = requirements.getOne(name)

    override fun addRequirement(name: String) = requirements.addRequirement(name)

    override fun deleteRequirement(name: String) = requirements.deleteRequirement(name)

    override fun editRequirement(name: String) = requirements.editRequirement(name)
}