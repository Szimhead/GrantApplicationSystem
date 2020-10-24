package pt.unl.fct.di.pt.firstdemo.model

import org.springframework.data.repository.CrudRepository
import pt.unl.fct.di.pt.firstdemo.services.AnswerDAO

interface AnswerRepository : CrudRepository<AnswerDAO, Long> {

}