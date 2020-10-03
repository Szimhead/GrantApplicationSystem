package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service

@Service
class ChairService {

    fun getAll() = listOf<ChairDTO>(ChairDTO(...))

    fun getOne(id:Long) = ChairDTO(...)

    fun addFinal(final:Long) = print("add chair działa")

    fun editFinal(id:Long) = print("edit chair działa")

    fun deleteFinal(id:Long) = print("delete chair działa")
}