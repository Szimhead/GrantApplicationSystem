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

    /* Panel handling */
    @GetMapping("/panel")
    fun getPanel():PanelDTO

    @PostMapping("/panel/{panelId}")
    fun addPanel(@PathVariable panelId: Long)

    /* Application handling */
    @GetMapping("/{title}/applications")
    fun getCallApplications(@PathVariable title: String): List<ApplicationDTO>

    @GetMapping("/{title}/applications/{id}")
    fun getOneCallApplication(@PathVariable title: String, @PathVariable id: Long): ApplicationDTO

    @PostMapping("/{title}/applications/{id}")
    fun addApplication(@PathVariable title: String, @PathVariable id: Long)

}