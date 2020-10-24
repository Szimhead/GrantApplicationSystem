package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pt.unl.fct.di.pt.firstdemo.api.CVRequirementDTO
import pt.unl.fct.di.pt.firstdemo.model.CVRequirementRepository

@Service
class CVRequirementService(val cvReq: CVRequirementRepository) {

    fun getAll() = cvReq.findAll()

    fun getOne(name: String) = cvReq.findByName(name)

    fun addRequirement(requirement: CVRequirementDAO) {
        cvReq.save(requirement)
    }

    @Transactional
    fun deleteRequirement(name: String) {
        val requirement = cvReq.findByName(name)
        cvReq.delete(requirement)
    }

    @Transactional
    fun editRequirement(name: String, requirement: CVRequirementDAO) {
        val editedReq = cvReq.findByName(name)
        editedReq.name = requirement.name
        editedReq.dataType = requirement.dataType
        editedReq.isMandatory = requirement.isMandatory
        cvReq.save(editedReq)
    }
}