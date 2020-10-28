package pt.unl.fct.di.pt.firstdemo

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import pt.unl.fct.di.pt.firstdemo.services.*
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

    @Autowired
    lateinit var reviewers: ReviewerService

    companion object {
        //val app1 = ApplicationDAO(1, Date(), 0, GrantCallDAO(), mutableListOf(), StudentDAO(), mutableListOf())
        //val app2 = ApplicationDAO(2, Date(), 1, GrantCallDAO(), mutableListOf(), StudentDAO(), mutableListOf())

        //needed to add application
        var student1 = StudentDAO()
        var app1 = ApplicationDAO()
        var app2 = ApplicationDAO()
        var app3 = ApplicationDAO()
        val grantCall1 = GrantCallDAO(0, "Grant Call", "some description", 20.0, Date(), Date(), mutableSetOf(), null, mutableSetOf())
        var grantCall2 = GrantCallDAO(0, "Second Grant Call", "Second description", 40.0, Date(), Date(), mutableSetOf(), null, mutableSetOf())
        var grantCall3 = GrantCallDAO(0, "Third Grant Call", "Third description", 60.0, Date(), Date(), mutableSetOf(), null, mutableSetOf())
        val institution1 = InstitutionDAO(0, "first institution", "first_inst_contact", mutableSetOf(), mutableSetOf())
        var reviewer1 = ReviewerDAO(0, "reviewer1", "reviewer1@email.com", "reviewer1.address", mutableSetOf(), mutableSetOf(), institution1, mutableSetOf())
        var dataItem1 = DataItemDAO(0, "dataItem1", "Long", true, mutableSetOf(), mutableSetOf())
        var dataItem2 = DataItemDAO(0, "dataItem2", "String", false, mutableSetOf(), mutableSetOf())
    }

    @Test
    fun `empty test on getAll applications`() {
        assertEquals(emptyList<ApplicationDAO>(), applications.getAll())
    }

    @Test
    fun `add Grant Call test`() {
        calls.addCall(grantCall1)

        assertEquals(setOf(grantCall1), calls.getAll().toSet())
        assertEquals(grantCall1, calls.getOne(grantCall1.id))
    }

    @Test
    fun `add Institution test`() {
        institutions.addInstitution(institution1)

        assertEquals(setOf(institution1), institutions.getAll().toSet())
        assertEquals(institution1, institutions.getOne(institution1.id))
    }

    @Test
    fun `add Student test`() {
        `add Institution test`() //must be able to add institutions

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
    fun `add one application to Grant Call test`() {
        `add Student test`() // must be able to add students (and thus institutions)
        `add Grant Call test`() // must be able to create Grant Calls

        app1 = ApplicationDAO(0, Date(), 0, grantCall1, mutableSetOf(), student1, mutableSetOf())

        calls.addApplication(grantCall1.id, app1, student1.id)

        grantCall1.applications.add(app1)
        student1.applications.add(app1)

        assertEquals(setOf(grantCall1), calls.getAll().toSet())     // verify that calls stay the same
        assertEquals(setOf(student1), students.getAll().toSet())    // verify that students stay the same
        assertEquals(setOf(app1), applications.getAll().toSet())    // verify that app1 is created and only one
        assertEquals(app1, applications.getOne(app1.id))            // verify that we can access app by id
        assertEquals(setOf(app1), calls.getOne(grantCall1.id).applications.toSet()) // verfiy that application is added to grant call
        assertEquals(setOf(app1), students.getOne(student1.id).applications.toSet())   // verify that application is added to student
        assertEquals(grantCall1, applications.getOne(app1.id).grantCall)               // verify that grant call is added to application
        assertEquals(student1, applications.getOne(app1.id).student)                   // verify that student is added to application
    }

    @Test
    fun `edit Grant Call test`() {
        `add one application to Grant Call test`()
        assertEquals(grantCall1, calls.getOne(grantCall1.id))
        val editedCall = GrantCallDAO(89, "edited title", "edited description", 100.0, Date(), Date(), mutableSetOf(), null, mutableSetOf())

        calls.editCall(grantCall1.id, editedCall)

        grantCall1.title = editedCall.title
        grantCall1.description = editedCall.description
        grantCall1.funding = editedCall.funding
        grantCall1.openDate = editedCall.openDate
        grantCall1.closeDate = editedCall.closeDate
        app1.grantCall = grantCall1

        assertEquals(grantCall1, calls.getOne(grantCall1.id))    //verifies that attributes have been changed and relations kept
    }

    @Test
    fun `add second Grant Call test`() {
        `edit Grant Call test`()
        calls.addCall(grantCall2)

        assertEquals(setOf(grantCall1, grantCall2), calls.getAll().toSet())
        assertEquals(grantCall2, calls.getOne(grantCall2.id))
    }


    @Test
    fun `delete second Grant call test`() {
        `add second Grant Call test`()

        calls.deleteCall(grantCall2.id)
        assertEquals(setOf(grantCall1), calls.getAll().toSet())
    }

    @Test
    fun `add third Grant Call test`() {
        `delete second Grant call test`()
        calls.addCall(grantCall3)

        assertEquals(setOf(grantCall1, grantCall3), calls.getAll().toSet())
        assertEquals(grantCall3, calls.getOne(grantCall3.id))
    }


    @Test
    fun `add second application to Grant Call test`() {
        `add third Grant Call test`()

        app2 = ApplicationDAO(0, Date(), 1, grantCall1, mutableSetOf(), student1, mutableSetOf())
        calls.addApplication(grantCall1.id, app2, student1.id)

        grantCall1.applications.add(app2)
        student1.applications.add(app2)

        assertEquals(setOf(grantCall1, grantCall3), calls.getAll().toSet())     // verify that calls stay the same
        assertEquals(setOf(student1), students.getAll().toSet())    // verify that students stay the same
        assertEquals(setOf(app1, app2), applications.getAll().toSet())    // verify that app1 is created and only one
        assertEquals(app2, applications.getOne(app2.id))            // verify that we can access app by id
        assertEquals(setOf(app1, app2), calls.getOne(grantCall1.id).applications.toSet()) // verfiy that application is added to grant call
        assertEquals(setOf(app1, app2), students.getOne(student1.id).applications.toSet())   // verify that application is added to student
        assertEquals(grantCall1, applications.getOne(app2.id).grantCall)               // verify that grant call is added to application
        assertEquals(student1, applications.getOne(app2.id).student)                   // verify that student is added to application
    }

    @Test
    fun `add third application to Grant Call test`() {
        `add second application to Grant Call test`()

        app3 = ApplicationDAO(0, Date(), 1, grantCall1, mutableSetOf(), student1, mutableSetOf())
        calls.addApplication(grantCall1.id, app3, student1.id)

        grantCall1.applications.add(app3)
        student1.applications.add(app3)

        assertEquals(setOf(grantCall1, grantCall3), calls.getAll().toSet())     // verify that calls stay the same
        assertEquals(setOf(student1), students.getAll().toSet())    // verify that students stay the same
        assertEquals(setOf(app1, app2, app3), applications.getAll().toSet())    // verify that app3 is created and only one
        assertEquals(app3, applications.getOne(app3.id))            // verify that we can access app by id
        assertEquals(setOf(app1, app2, app3), calls.getOne(grantCall1.id).applications.toSet()) // verfiy that application is added to grant call
        assertEquals(setOf(app1, app2, app3), students.getOne(student1.id).applications.toSet())   // verify that application is added to student
        assertEquals(grantCall1, applications.getOne(app3.id).grantCall)               // verify that grant call is added to application
        assertEquals(student1, applications.getOne(app3.id).student)                   // verify that student is added to application
    }

    @Test
    fun `remove application test`() {
        `add third application to Grant Call test`()

        calls.deleteApplication(grantCall1.id, app3.id)

        grantCall1.applications.remove(app3)
        student1.applications.remove(app3)

        assertEquals(setOf(grantCall1, grantCall3), calls.getAll().toSet())     // verify that calls stay the same
        assertEquals(setOf(student1), students.getAll().toSet())    // verify that students stay the same
        assertEquals(setOf(app1, app2), applications.getAll().toSet())    // verify that app3 is deleted
        assertEquals(setOf(app1, app2), calls.getOne(grantCall1.id).applications.toSet()) // verfiy that application is deleted from grant call
        assertEquals(setOf(app1, app2), students.getOne(student1.id).applications.toSet())   // verify that application is deleted from student
    }

    @Test
    fun `getCallApplications with two applications test`() {
        `add second application to Grant Call test`()

        assertEquals(setOf(app1, app2), calls.getCallApplications(grantCall1.id))
    }

    @Test
    fun `basic get(empty)PanelFromGrantCall test`() {
        `getCallApplications with two applications test`()

        val panel = calls.getPanelFromGrantCall(grantCall3.id)

        // verify that panel is in fact empty and that it corresponds to the right grant call
        assertEquals(panel.grantCall, grantCall3)
        assertEquals(panel.chair, null)
        assertEquals(panel.reviewers, mutableSetOf<ReviewerDAO>())
    }

    @Test
    fun `basic get(empty)AllReviewers test`() {
        `basic get(empty)PanelFromGrantCall test`()

        assertEquals(emptyList<ReviewerDAO>(), reviewers.getAll())
    }

    @Test
    fun `add reviewer test`() {
        `basic get(empty)AllReviewers test`()

        reviewers.addReviewer(reviewer1)

        assertEquals(setOf(reviewer1), reviewers.getAll().toSet())
        assertEquals(reviewer1, reviewers.getOne(reviewer1.id))
    }

    @Test
    fun `add reviewer to panel test`() {
        `add reviewer test`()

        assertEquals(grantCall3, calls.getOne(grantCall3.id))

        calls.addReviewerToPanel(grantCall3.id, reviewer1.id)

        val panel = calls.getPanelFromGrantCall(grantCall3.id)
        reviewer1.panels.add(panel)
        grantCall3.panel = panel;

        assertEquals(setOf(reviewer1), reviewers.getAll().toSet()) // verify that reviewer was added
        assertEquals(reviewer1, reviewers.getOne(reviewer1.id))  // verify get reviewer by id
        assertEquals(setOf(reviewer1), calls.getReviewers(grantCall3.id)) // verify that reviewer was added to right panel
        assertEquals(setOf(calls.getPanelFromGrantCall(grantCall3.id)), reviewers.getOne(reviewer1.id).panels)

    }

    @Test
    fun `delete reviewer from panel test`() {
        `add reviewer to panel test`()

        assertEquals(grantCall3, calls.getOne(grantCall3.id))

        calls.deleteReviewerFromPanel(grantCall3.id, reviewer1.id)

        reviewer1.panels = mutableSetOf()
        grantCall3.panel?.reviewers = mutableSetOf()


        assertEquals(setOf(reviewer1), reviewers.getAll().toSet()) // verify that reviewer still exists
        assertEquals(reviewer1, reviewers.getOne(reviewer1.id))  // verify get reviewer by id
        assertEquals(emptySet<ReviewerDAO>(), calls.getReviewers(grantCall3.id)) // verify that reviewer was deleted
        assertEquals(setOf(grantCall1, grantCall3), calls.getAll().toSet()) // verify that no other changes have been made
    }

    @Test
    fun `get empty dataItems from grant call test`() {
        `delete reviewer from panel test`()

        assertEquals(emptyList<DataItemDAO>(), calls.getAllDataItems(grantCall1.id).toList())
    }

    @Test
    fun `add DataItem to grant call test`() {
        `get empty dataItems from grant call test`()

        calls.addDataItem(grantCall1.id, dataItem1)
        grantCall1.dataItems.add(dataItem1)
        dataItem1.grantCalls.add(grantCall1)

        assertEquals(setOf(grantCall1, grantCall3), calls.getAll().toSet()) // verify that grant calls are correct
        assertEquals(setOf(dataItem1), calls.getAllDataItems(grantCall1.id)) // verifiy getalldataitems
        assertEquals(dataItem1, calls.getOneDataItem(grantCall1.id, dataItem1.id)) // verify getonedataItem
        assertEquals(dataItem1.grantCalls, calls.getOneDataItem(grantCall1.id, dataItem1.id).grantCalls) // verify that grant call was added to dataitem
    }

    @Test
    fun `add second dataItem to grant call test`() {
        `add DataItem to grant call test`()

        calls.addDataItem(grantCall1.id, dataItem2)
        grantCall1.dataItems.add(dataItem2)
        dataItem2.grantCalls.add(grantCall1)

        assertEquals(setOf(grantCall1, grantCall3), calls.getAll().toSet()) // verify that grant calls are correct
        assertEquals(setOf(dataItem1, dataItem2), calls.getAllDataItems(grantCall1.id)) // verify getalldataitems
        assertEquals(dataItem2, calls.getOneDataItem(grantCall1.id, dataItem2.id)) // verify getonedataItem
        assertEquals(dataItem2.grantCalls, calls.getOneDataItem(grantCall1.id, dataItem2.id).grantCalls) // verify that grant call was added to dataitem
    }

    @Test
    fun `delete second DataItem from grant call test`() {
        `add second dataItem to grant call test`()

        assertEquals(dataItem2, calls.getOneDataItem(grantCall1.id, dataItem2.id))

        calls.deleteDataItem(grantCall1.id, dataItem2.id)

        grantCall1.dataItems.remove(dataItem2)
        dataItem2.grantCalls.remove(grantCall1)

        assertEquals(setOf(grantCall1, grantCall3), calls.getAll().toSet()) // verify that grant calls are correct
        assertEquals(setOf(dataItem1), calls.getAllDataItems(grantCall1.id)) // verify that data item was removed
    }

    @Test
    fun `edit data item test`() {
        `delete second DataItem from grant call test`()

        assertEquals(dataItem1, calls.getOneDataItem(grantCall1.id, dataItem1.id))
        grantCall1.dataItems.remove(dataItem1)
        val editedDataItem =  DataItemDAO(0, "edited data item", "edited type", false, mutableSetOf(), mutableSetOf())
        calls.editDataItem(grantCall1.id, dataItem1.id, editedDataItem)

        dataItem1.name = editedDataItem.name
        dataItem1.dataType = editedDataItem.dataType
        dataItem1.isMandatory = editedDataItem.isMandatory

        grantCall1.dataItems.add(dataItem1)

        assertEquals(setOf(grantCall1, grantCall3), calls.getAll().toSet()) // verify that grant calls are correct
        assertEquals(setOf(dataItem1), calls.getAllDataItems(grantCall1.id)) // verify that data item is correctly edited
        assertEquals(dataItem1, calls.getOneDataItem(grantCall1.id, dataItem1.id)) // verify getonedataItem
        assertEquals(dataItem1.grantCalls, calls.getOneDataItem(grantCall1.id, dataItem1.id).grantCalls) // verify that relation was kept
    }





}
