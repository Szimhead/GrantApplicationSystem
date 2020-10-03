package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import pt.unl.fct.di.pt.firstdemo.api.ChairDTO

@Service
class ChairService {

    fun getAll() = listOf<ChairDTO>(ChairDTO(2, 1, 10), ChairDTO(21, 5, 2))

    fun getOne(id:Long) = ChairDTO(20, 2, 1)

    fun addChair(final:Long) = print("add chair działa")

    fun editChair(id:Long) = print("edit chair działa")

    fun deleteChair(id:Long) = print("delete chair działa")
}