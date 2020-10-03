package pt.unl.fct.di.pt.firstdemo.api

import java.util.Date

data class ApplicationDTO(val id:Long, val submissionDate: Date, val status:Int)

data class GrantCallDTO(val title:String)

data class InstitutionDTO(val id:Long, val name:String)

data class StudentDTO(val studentNr:Long, val name:String)

data class ReviewerDTO(val ReviewerNr:Long, val name:String)