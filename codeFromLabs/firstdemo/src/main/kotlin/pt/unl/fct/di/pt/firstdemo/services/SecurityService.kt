package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import pt.unl.fct.di.pt.firstdemo.model.ApplicationRepository

@Component
class SecurityService(val applications: ApplicationRepository) {

    fun canAddApplication( user: UserDAO){

    }

    fun canEditApplication( user: UserDAO, applicationId: Long){

    }

    fun canDeleteApplication( user: UserDAO, applicationId: Long){

    }

    fun canGetApplication( user: UserDAO, applicationId: Long){

    }

    fun canGetStudentApplications( user: UserDAO){

    }

    fun canDeleteApplication( user: UserDAO, applicationId: Long){

    }



}