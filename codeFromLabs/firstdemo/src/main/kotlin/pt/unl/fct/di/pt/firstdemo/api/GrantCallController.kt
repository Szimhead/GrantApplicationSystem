package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.di.pt.firstdemo.services.GrantCallService

@RestController
class GrantCallController(val calls:GrantCallService): GrantCallAPI {

    override fun getAll() = calls.getAll()

    override fun getAllOpen() = calls.getAllOpen()

    override fun getOne(title: String) = calls.getOne(title)

    override fun addCall() = calls.addCall()

    override fun editCall(title: String) = calls.editCall(title)

    override fun deleteCall(title: String) = calls.deleteCall(title)
}