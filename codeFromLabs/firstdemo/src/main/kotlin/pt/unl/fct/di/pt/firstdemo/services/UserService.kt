package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.UserRepository
import java.util.*

@Service
class UserService(var users: UserRepository) {

    fun findUser(username:String): UserDAO {
        return users.findById(username).orElseThrow{
            NotFoundException("User with username $username not found")
        }
    }

    fun addUser(user: UserDAO) {
        val aUser = users.findById(user.username).orElse(null)

        if ( aUser == null ) {
            user.password = BCryptPasswordEncoder().encode(user.password)
            Optional.of(users.save(user))
        }
    }

    fun getRoles(user: UserDAO): String {
        return when (val role = user.role) {
            "STUDENT" -> "USER,$role"
            "REVIEWER" -> "USER,$role"
            "CHAIR" -> "USER,REVIEWER,$role"
            "SPONSOR" -> "USER,$role"
            else -> ""
        }
    }

    fun changeRole(user: UserDAO, newRole: String) {
        user.role = newRole
        users.save(user)
    }
}