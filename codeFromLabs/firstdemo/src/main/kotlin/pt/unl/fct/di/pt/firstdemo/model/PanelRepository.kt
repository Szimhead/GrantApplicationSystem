package pt.unl.fct.di.pt.firstdemo.model

import org.springframework.data.repository.CrudRepository
import pt.unl.fct.di.pt.firstdemo.services.PanelDAO

interface PanelRepository : CrudRepository<PanelDAO, Long> {

}