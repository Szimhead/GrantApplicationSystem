package pt.unl.fct.di.pt.firstdemo.api

import java.util.Date

data class UserDTO(val id: Long, val name: String, val email: String, val address: String)

data class OrganizationDTO(val id: Long, val name: String, val contact: String)

data class GrantCallDTO(val title:String, val description: String, val funding: Double, val openDate: Date, val closeDate: Date)

data class ApplicationDTO(val id:Long, val submissionDate: Date, val status:Int)

data class PanelDTO(val id: Long)

data class ReviewDTO(val id:Long, val isAccepted: Boolean, val comment: String)

data class DataItemDTO(val name: String, val datatype: String, val isMandatory: Boolean)

data class CVRequirementDTO(val name: String, val datatype: String, val isMandatory: Boolean)

data class AnswerDTO(val name: String, val value: String, val datatype: String)

data class CVItemDTO(val name: String, val value: String, val datatype: String)

data class CVDTO(val items: List<CVItemDTO>)