package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/CV")
interface CVAPI {

    @GetMapping("/{id}")
    fun getOne(@PathVariable id:Long):CVDTO

    @DeleteMapping("/id")
    fun deleteCV(@PathVariable id:Long)
}