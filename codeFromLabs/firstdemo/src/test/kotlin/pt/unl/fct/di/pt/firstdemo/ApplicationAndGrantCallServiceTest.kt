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

        assertEquals(listOf(grantCall1), calls.getAll().toList())
        assertEquals(grantCall1, calls.getOne("Grant Call"))
    }

    @Test
    fun `add and get Institutions test`() {
        institutions.addInstitution(institution1)

        assertEquals(listOf(institution1), institutions.getAll().toList())
        assertEquals(institution1, institutions.getOne(institution1.id))
    }

    @Test
    fun `add and get Students test`() {
        `add and get Institutions test`() //must be able to add institutions


        val student1 = StudentDAO(0, "Tiago", "tiago@email.com", "tiago's street n2", mutableSetOf(), institution1, null)

        institutions.addStudent(institution1.id, student1)

        institution1.students.add(student1) //add student to our institution mockup

        assertEquals(listOf(institution1), institutions.getAll().toList()) // verify that no institution is added
        assertEquals(listOf(student1), students.getAll().toList())         // verify that only one student is added
        assertEquals(student1, students.getOne(student1.id))               // verify that we can access student by id
        assertEquals(listOf(student1), institutions.getStudents(institution1.id).toList())  // verify that student is added to institution
        assertEquals(institution1, students.getOne(student1.id).institution) // verify that institution is added to student
    }

    @Test
    fun `add applications to Grant Call and get applications all from grant call test`() {
        `add and get Institutions test`()
        `add and get Grant Calls test`()



        val app1 = ApplicationDAO(1, Date(), 0, grantCall1, mutableListOf(), StudentDAO(), mutableListOf())

        calls.addApplication(grantCall1.title, app1)
      //  calls.addApplication(grantCall1.title, app2)

        assertEquals(setOf(app1), calls.getCallApplications(grantCall1.title))
    }


}
