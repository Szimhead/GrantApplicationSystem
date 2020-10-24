package pt.unl.fct.di.pt.firstdemo.model

import org.springframework.data.repository.CrudRepository
import pt.unl.fct.di.pt.firstdemo.services.DataItemDAO
import pt.unl.fct.di.pt.firstdemo.services.GrantCallDAO

interface DataItemRepository : CrudRepository<DataItemDAO, Long> {
    fun findByName(name: String) : DataItemDAO
    fun findByNameAndGrantCalls(name: String, gc: GrantCallDAO) : DataItemDAO
}