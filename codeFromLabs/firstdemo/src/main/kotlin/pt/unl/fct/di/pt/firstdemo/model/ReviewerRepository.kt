package pt.unl.fct.di.pt.firstdemo.model

import org.springframework.data.repository.CrudRepository
import pt.unl.fct.di.pt.firstdemo.services.ReviewerDAO

interface ReviewerRepository : CrudRepository<ReviewerDAO, Long> {

}