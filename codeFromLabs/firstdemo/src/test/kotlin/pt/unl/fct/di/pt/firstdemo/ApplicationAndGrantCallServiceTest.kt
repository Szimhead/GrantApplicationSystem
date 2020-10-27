package pt.unl.fct.di.pt.firstdemo

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertNotEquals
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import pt.unl.fct.di.pt.firstdemo.api.UserDTO
import pt.unl.fct.di.pt.firstdemo.services.*
import java.time.LocalDate
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest
class ApplicationAndGrantCallServiceTest() {

    @Autowired
    lateinit var applications:ApplicationService

    @Autowired
    lateinit var calls:GrantCallService

    @Autowired
    lateinit var students:StudentService

    @Autowired
    lateinit var institutions: InstitutionService

    companion object {
        //val app1 = ApplicationDAO(1, Date(), 0, GrantCallDAO(), mutableListOf(), StudentDAO(), mutableListOf())
        //val app2 = ApplicationDAO(2, Date(), 1, GrantCallDAO(), mutableListOf(), StudentDAO(), mutableListOf())

        //needed to add application
        var student1 = StudentDAO()
        var app1 = ApplicationDAO()
        val grantCall1 = GrantCallDAO(0, "Grant Call", "some description", 20.0, Date(), Date(), mutableSetOf(), null, emptySet())
        val institution1 = InstitutionDAO(0, "first institution", "first_inst_contact", mutableSetOf(), mutableSetOf())
    }

    @Test
    fun `empty test on getAll applications`() {
        assertEquals(emptyList<ApplicationDAO>(), applications.getAll())
    }

    @Test
    fun `add and get Grant Calls test`() {
        calls.addCall(grantCall1)

        assertEquals(setOf(grantCall1), calls.getAll().toSet())
        assertEquals(grantCall1, calls.getOne("Grant Call"))
    }

    @Test
    fun `add and get Institutions test`() {
        institutions.addInstitution(institution1)

        assertEquals(setOf(institution1), institutions.getAll().toSet())
        assertEquals(institution1, institutions.getOne(institution1.id))
    }

    @Test
    fun `add and get Students test`() {
        `add and get Institutions test`() //must be able to add institutions

        student1 = StudentDAO(0, "Tiago", "tiago@email.com", "tiago's street n2", mutableSetOf(), institution1, null)

        institutions.addStudent(institution1.id, student1)

        institution1.students.add(student1) //add student to our institution mockup

        assertEquals(setOf(institution1), institutions.getAll().toSet()) // verify that no institution is added
        assertEquals(setOf(student1), students.getAll().toSet())         // verify that only one student is added
        assertEquals(student1, students.getOne(student1.id))               // verify that we can access student by id
        assertEquals(setOf(student1), institutions.getStudents(institution1.id).toSet())  // verify that student is added to institution
        assertEquals(institution1, students.getOne(student1.id).institution) // verify that institution is added to student
    }

    @Test
    fun `add one application to Grant Call and get test`() {
        `add and get Students test`() // must be able to add students (and thus institutions)
        `add and get Grant Calls test`() // must be able to create Grant Calls

        app1 = ApplicationDAO(0, Date(), 0, grantCall1, mutableSetOf(), student1, mutableSetOf())

        calls.addApplication(grantCall1.title, app1, student1.id)

        grantCall1.applications.add(app1)
        student1.applications.add(app1)

        assertEquals(setOf(grantCall1), calls.getAll().toSet())     // verify that calls stay the same
        assertEquals(setOf(student1), students.getAll().toSet())    // verify that students stay the same
        assertEquals(setOf(app1), applications.getAll().toSet())    // verify that app1 is created and only one
        assertEquals(app1, applications.getOne(app1.id))            // verify that we can access app by id
        assertEquals(setOf(app1), calls.getOne(grantCall1.title).applications.toSet()) // verfiy that application is added to grant call
        assertEquals(setOf(app1), students.getOne(student1.id).applications.toSet())   // verify that application is added to student
        assertEquals(grantCall1, applications.getOne(app1.id).grantCall)               // verify that grant call is added to application
        assertEquals(student1, applications.getOne(app1.id).student)                   // verify that student is added to application
    }

    @Test
    fun `edit Grant Call test`() {
        `add one application to Grant Call and get test`()

        val editedCall = GrantCallDAO()
    }


}
