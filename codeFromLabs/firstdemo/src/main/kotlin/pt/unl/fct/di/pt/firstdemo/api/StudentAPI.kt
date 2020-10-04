package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.*

@RequestMapping("/students")
interface StudentAPI {

    @GetMapping("")
    fun getAll(): List<StudentDTO>

    @GetMapping("/{studentNr}")
    fun getOne(@PathVariable studentNr:Long): StudentDTO

    @DeleteMapping("/{studentNr}")
    fun deleteStudent(@PathVariable studentNr: Long)

    @PutMapping("{studentNr}")
    fun editStudent(@PathVariable studentNr: Long)

    /* application handling */
    @GetMapping("/{studentNr}/applications")
    fun getApplications(@PathVariable studentNr: Long): List<ApplicationDTO>

    @GetMapping("/{studentNr}/applications/{id}")
    fun getOneApplication(@PathVariable studentNr: Long, @PathVariable id:Long): ApplicationDTO

}