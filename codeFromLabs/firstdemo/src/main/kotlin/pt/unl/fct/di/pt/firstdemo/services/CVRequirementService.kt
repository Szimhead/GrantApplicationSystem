package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.CVRequirementDTO

@Service
class CVRequirementService {
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