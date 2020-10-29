package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pt.unl.fct.di.pt.firstdemo.api.CVRequirementDTO
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.CVRequirementRepository

@Service
class CVRequirementService(val cvReq: CVRequirementRepository) {

    fun getAll() = cvReq.findAll()

    fun getOne(id: Long) = cvReq.findById(id).orElseThrow {
        NotFoundException("CV requirement with id $id not found")
    }

    fun addRequirement(requirement: CVRequirementDAO) {
        cvReq.save(requirement)
    }

    @Transactional
    fun deleteRequirement(requirement: CVRequirementDAO) {
        cvReq.delete(requirement)
    }

    @Transactional
    fun editRequirement(editedReq: CVRequirementDAO, newReq: CVRequirementDAO) {
        editedReq.name = newReq.name
        editedReq.dataType = newReq.dataType
        editedReq.isMandatory = newReq.isMandatory
    }
}