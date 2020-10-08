package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*

@RequestMapping("/sponsors")
interface SponsorAPI {
    @GetMapping
    fun getAll():List<OrganizationDTO>

    @GetMapping("/{id}")
    fun getOne(@PathVariable id:Long): OrganizationDTO

    @PostMapping("")
    fun addSponsor()

    @DeleteMapping("/{id}")
    fun deleteSponsor(@PathVariable id:Long)

    @PutMapping("/{id}")
    fun editSponsor(@PathVariable id:Long)


    /* grant call handling */
    @GetMapping("/{id}/grant_calls")
    fun getGrantCalls(@PathVariable id:Long): List<GrantCallDTO>

    @PostMapping("/{id}/grant_calls")
    fun addGrantCall(@PathVariable id:Long)

}