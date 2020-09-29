package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@RequestMapping("/applications")
interface ApplicationAPI {
    @GetMapping
    fun getAll():List<ApplicationDTO>

    @GetMapping("/{id}")
    fun getOne(@PathVariable id:Long): ApplicationDTO
}