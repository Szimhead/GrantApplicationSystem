package pt.unl.fct.di.pt.firstdemo.model

import org.springframework.data.repository.CrudRepository
import pt.unl.fct.di.pt.firstdemo.services.GrantCallDAO
import java.util.*

interface GrantCallRepository : CrudRepository<GrantCallDAO, Long> {
    fun findByOpenDateBeforeAndCloseDateAfter(before: Date, after: Date):List<GrantCallDAO>
    fun findByTitle(title: String): GrantCallDAO
}