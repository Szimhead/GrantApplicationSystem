package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.di.pt.firstdemo.services.DataItemDAO
import pt.unl.fct.di.pt.firstdemo.services.DataItemService

@RestController
class DataItemController(val items: DataItemService) : DataItemAPI {
    override fun getAll(): List<DataItemDTO> = items.getAll().map { DataItemDTO(it) }

    override fun getOne(id: Long): DataItemDTO = DataItemDTO(items.getOne(id))

    override fun addDataItem(dataItem: DataItemDTO) = items.addDataItem(DataItemDAO(dataItem))

}