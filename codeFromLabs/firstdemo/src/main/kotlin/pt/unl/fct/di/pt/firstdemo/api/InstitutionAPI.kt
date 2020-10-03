package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*

@RequestMapping("/institutions")
interface InstitutionAPI {
    @GetMapping
    fun getAll():List<InstitutionDTO>

    @GetMapping("/{id}")
    fun getOne(@PathVariable id:Long): InstitutionDTO

    @PostMapping("")
    fun addInstitution()

    @DeleteMapping("/{id}")
    fun deleteInstitution(@PathVariable id:Long)

    @PutMapping("/{id}")
    fun updateInstitution(@PathVariable id:Long)


    /* student handling */
    @GetMapping("/{id}/students")
    fun getStudents(@PathVariable id:Long): List<StudentDTO>

    @GetMapping("/{id}/students/{studentNr}")
    fun getOneStudent(@PathVariable id:Long, @PathVariable studentNr:Long): StudentDTO

    @PostMapping("/{id}/students")
    fun addStudent(@PathVariable id:Long)


    /* reviewer handling */
    @GetMapping("/{id}/reviewers")
    fun getReviewers(@PathVariable id:Long): List<ReviewerDTO>

    @GetMapping("/{id}/reviewers/{reviewerNr}")
    fun getOneReviewer(@PathVariable id:Long, @PathVariable reviewerNr:Long): ReviewerDTO

    @PostMapping("/{id}/reviewers")
    fun addReviewer(@PathVariable id:Long)

}