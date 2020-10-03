package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.api.FinalDTO

@Service
class FinalService {

    fun getAll() = listOf<FinalDTO>(FinalDTO(0, "accepted", "barely, but ok"),
            FinalDTO(1, "accepted", "better"))

    fun getOne(id:Long) = FinalDTO(1, "declined", "best one so far")

    fun addFinal(final:Long) = print("add final działa")

    fun editFinal(id:Long) = print("edit final działa")

    fun deleteFinal(id:Long) = print("delete final działa")
}