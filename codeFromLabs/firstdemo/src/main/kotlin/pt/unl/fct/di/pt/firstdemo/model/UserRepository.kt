package pt.unl.fct.di.pt.firstdemo.model

import org.springframework.data.repository.CrudRepository
import pt.unl.fct.di.pt.firstdemo.services.GrantCallDAO
import pt.unl.fct.di.pt.firstdemo.services.UserDAO
import java.util.*

interface UserRepository : CrudRepository<UserDAO, Long> {
    fun findByUsername(username: String): Optional<UserDAO>
}