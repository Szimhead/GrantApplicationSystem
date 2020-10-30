package pt.unl.fct.di.pt.firstdemo.model

import org.springframework.data.repository.CrudRepository
import pt.unl.fct.di.pt.firstdemo.services.UserDAO.StudentDAO
import pt.unl.fct.di.pt.firstdemo.services.UserDAO

interface StudentRepository : CrudRepository<StudentDAO, Long> {
}