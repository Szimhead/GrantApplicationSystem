package pt.unl.fct.di.pt.firstdemo.services

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.model.UserRepository
import java.util.*

@Service
class UserService(var users: UserRepository) {
/*
    fun findUser(username:String): Optional<UserDAO> = users.findById(username)

    fun addUser(user: UserDAO) : Optional<UserDAO> {
        val aUser = users.findById(user.username)

        return if ( aUser.isPresent )
            Optional.empty()
        else {
            user.password = BCryptPasswordEncoder().encode(user.password)
            Optional.of(users.save(user))
        }
    } */
}