package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*

@RequestMapping("/institutions")
interface InstitutionAPI {
    @GetMapping
    fun getAll():List<OrganizationDTO>

    @GetMapping("/{id}")
    fun getOne(@PathVariable id:Long): OrganizationDTO

    @PostMapping("")
    fun addInstitution()

    @DeleteMapping("/{id}")
    fun deleteInstitution(@PathVariable id:Long)

    @PutMapping("/{id}")
    fun editInstitution(@PathVariable id:Long)


    /* student handling */
    @GetMapping("/{id}/students")
    fun getStudents(@PathVariable id:Long): List<UserDTO>

    @GetMapping("/{id}/students/{studentNr}")
    fun getOneStudent(@PathVariable id:Long, @PathVariable studentNr:Long): UserDTO

    @PostMapping("/{id}/students")
    fun addStudent(@PathVariable id:Long)


    /* reviewer handling */
    @GetMapping("/{id}/reviewers")
    fun getReviewers(@PathVariable id:Long): List<UserDTO>

    @GetMapping("/{id}/reviewers/{reviewerNr}")
    fun getOneReviewer(@PathVariable id:Long, @PathVariable reviewerNr:Long): UserDTO

    @PostMapping("/{id}/reviewers")
    fun addReviewer(@PathVariable id:Long)

}