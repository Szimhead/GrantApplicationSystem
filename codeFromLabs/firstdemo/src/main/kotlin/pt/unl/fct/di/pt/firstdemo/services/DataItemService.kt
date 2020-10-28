package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.model.DataItemRepository

@Service
class DataItemService(val dataItems: DataItemRepository) {
    fun getAll() : Iterable<DataItemDAO> = dataItems.findAll()

    fun getOne(id:Long) : DataItemDAO = dataItems.findById(id).orElseThrow {
        NotFoundException("Data Item with id $id not found")
    }

}