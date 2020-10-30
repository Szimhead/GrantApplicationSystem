package pt.unl.fct.di.pt.firstdemo.model

import org.springframework.data.repository.CrudRepository
import pt.unl.fct.di.pt.firstdemo.services.UserDAO.SponsorDAO
import pt.unl.fct.di.pt.firstdemo.services.UserDAO

interface SponsorRepository : CrudRepository<SponsorDAO, Long> {

}