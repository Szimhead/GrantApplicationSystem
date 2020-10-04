package pt.unl.fct.di.pt.firstdemo.api

import java.util.Date

data class ApplicationDTO(val id:Long, val submissionDate: Date, val status:Int)

data class GrantCallDTO(val title:String)

data class InstitutionDTO(val id:Long, val name:String, val contact: String)

data class StudentDTO(val studentNr:Long, val name:String)

data class ReviewerDTO(val ReviewerNr:Long, val name:String)

data class FinalDTO(val id:Long, val grade:String, val review: String)

data class PanelDTO(val panelId:Long, val grantTitle:String, val reviewers: List<ReviewerDTO>)

data class ReviewDTO(val id:Long, val isAccepted: Boolean, val comment: String)

data class SponsorDTO(val id:Long, val name: String, val contact: String)

data class CVDTO(val id:Long)