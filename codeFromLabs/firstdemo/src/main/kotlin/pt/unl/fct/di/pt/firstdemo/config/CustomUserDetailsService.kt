package pt.unl.fct.di.pt.firstdemo.config

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.services.ReviewerService
import pt.unl.fct.di.pt.firstdemo.services.SponsorService
import pt.unl.fct.di.pt.firstdemo.services.StudentService
import pt.unl.fct.di.pt.firstdemo.services.UserService

class CustomUserDetails (
    private val aUsername:String,
    private val aPassword:String,
    private val someAuthorities:MutableCollection<out GrantedAuthority>) : UserDetails {

        override fun getAuthorities(): MutableCollection<out GrantedAuthority> = someAuthorities

        override fun isEnabled(): Boolean = true

        override fun getUsername(): String = aUsername

        override fun isCredentialsNonExpired(): Boolean = true

        override fun getPassword(): String = aPassword

        override fun isAccountNonExpired(): Boolean = true

        override fun isAccountNonLocked(): Boolean = true
    }


    @Service
    class CustomUserDetailsService(
            val users: UserService
    ) : UserDetailsService {

        override fun loadUserByUsername(username: String?): UserDetails {

            username?.let {
                var userDAO = users.findUser(it)
                if( userDAO.isPresent ) {
                    return CustomUserDetails(userDAO.get().username, userDAO.get().password, mutableListOf())
                } else
                    throw UsernameNotFoundException(username)
            }
            throw UsernameNotFoundException(username)
        }
}