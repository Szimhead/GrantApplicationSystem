package pt.unl.fct.di.pt.firstdemo.services

import org.springframework.stereotype.Service

@Service
class GrantCallService {
    fun getAll() = listOf<GrantCallDTO>(GrantCallDTO("all success"))

    fun getAllOpen() = listOf<GrantCallDTO>(GrantCallDTO("all open success"))

    fun getOne(title: String) = GrantCallDTO("one with title: $title")

    fun addCall() {
        TODO("Not yet implemented")
    }

    fun editCall(title: String) {
        TODO("Not yet implemented")
    }

    fun deleteCall(title: String) {
        TODO("Not yet implemented")
    }

}