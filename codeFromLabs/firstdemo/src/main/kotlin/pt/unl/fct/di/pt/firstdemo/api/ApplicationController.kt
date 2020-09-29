package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.di.pt.firstdemo.services.ApplicationService
import java.util.*

@RestController
@RequestMapping("/applications")
class ApplicationController(val applications: ApplicationService): ApplicationAPI {

    @GetMapping
    override fun getAll() = applications.getAll()

    @GetMapping("/{id}")
    override fun getOne(@PathVariable id:Long) = applications.getOne(id)
}