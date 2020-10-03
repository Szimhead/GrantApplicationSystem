package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*

@RequestMapping("/calls")
interface GrantCallAPI {

    @GetMapping("")
    fun getAll():List<GrantCallDTO>

    @GetMapping("/open")
    fun getAllOpen():List<GrantCallDTO>

    @GetMapping("/{title}") //assuming title is unique
    fun getOne(@PathVariable title:String):GrantCallDTO

    @PostMapping("")
    fun addCall(/*TODO*/)

    @PutMapping("/{title}")
    fun editCall(@PathVariable title:String)

    @DeleteMapping("/{title}")
    fun deleteCall(@PathVariable title: String)

}