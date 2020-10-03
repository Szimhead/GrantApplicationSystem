package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@Service
class FinalService {

    fun getAll() = listOf<FinalDTO>(FinalDTO(...))

    fun getOne(id:Long) = FinalDTO(...)

    fun addFinal(final:Long) = print("add final działa")

    fun editFinal(id:Long) = print("edit final działa")

    fun deleteFinal(id:Long) = print("delete final działa")
}