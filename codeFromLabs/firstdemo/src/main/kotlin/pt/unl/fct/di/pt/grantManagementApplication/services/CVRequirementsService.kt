package pt.unl.fct.di.pt.grantManagementApplication.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.grantManagementApplication.api.CVRequirementDTO

@Service
class CVRequirementsService {
    fun getAll() = listOf<CVRequirementDTO>()

    fun getOne(name: String) = CVRequirementDTO("age", "Int", true)

    fun addRequirement(name: String) {
        TODO("Not yet implemented")
    }

    fun deleteRequirement(name: String) {
        TODO("Not yet implemented")
    }

    fun editRequirement(name: String) {
        TODO("Not yet implemented")
    }
}