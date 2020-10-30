package pt.unl.fct.di.pt.firstdemo.serviceTests

import junit.framework.Assert.assertEquals
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

    @Autowired
    lateinit var sponsors: SponsorService

    @Autowired
    lateinit var dataItems: DataItemService

    companion object {
        //val app1 = ApplicationDAO(1, Date(), 0, GrantCallDAO(), mutableListOf(), StudentDAO(), mutableListOf())
        //val app2 = ApplicationDAO(2, Date(), 1, GrantCallDAO(), mutableListOf(), StudentDAO(), mutableListOf())

        //needed to add application
        var student1 = StudentDAO()
        var app1 = ApplicationDAO()
        var app2 = ApplicationDAO()
        var app3 = ApplicationDAO()
        val sponsor1 = SponsorDAO(0, "sponsor1", "sponsor1 contact", mutableSetOf())
        var grantCall1 = GrantCallDAO()
        var grantCall2 = GrantCallDAO()
        var grantCall3 = GrantCallDAO()
        val institution1 = InstitutionDAO(0, "first institution", "first_inst_contact", mutableSetOf(), mutableSetOf())
        var reviewer1 = ReviewerDAO()
        var dataItem1 = DataItemDAO()
        var dataItem2 = DataItemDAO()
    }

    @Test
    fun `empty test on getAll applications`() {
        assertEquals(emptyList<ApplicationDAO>(), applications.getAll())
    }

    @Test
    fun `add sponsor test`() {
        sponsors.addSponsor(sponsor1)

        assertEquals(setOf(sponsor1), sponsors.getAll().toSet())
        assertEquals(sponsor1, sponsors.getOne(sponsor1.id))
    }

    @Test
    fun `add Grant Call test`() {
        `add sponsor test`()

        grantCall1 = GrantCallDAO(0, "Grant Call", "some description", 20.0, Date(), Date(), mutableSetOf(), null, mutableSetOf(), sponsors.getOne(sponsor1.id))

        sponsors.addGrantCall(sponsors.getOne(sponsor1.id), grantCall1)

        sponsor1.grantCalls.add(grantCall1)
        grantCall1.sponsor = sponsor1

        assertEquals(setOf(grantCall1), calls.getAll().toSet())
        assertEquals(setOf(sponsor1), sponsors.getAll().toSet())
        assertEquals(sponsor1, sponsors.getOne(sponsor1.id))
        assertEquals(grantCall1, calls.getOne(grantCall1.id))
        assertEquals(sponsor1, calls.getOne(grantCall1.id).sponsor)
        assertEquals(setOf(grantCall1), sponsors.getGrantCallsFromSponsor(sponsors.getOne(sponsor1.id)).toSet())
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

        student1 = StudentDAO(0, "Tiago", "tiago@email.com", "tiago's street n2", mutableSetOf(), institutions.getOne(institution1.id), null)

        institutions.addStudentToInstitution(institutions.getOne(institution1.id), student1)

        institution1.students.add(student1) //add student to our institution mockup
        student1.institution = institution1

        assertEquals(setOf(institution1), institutions.getAll().toSet()) // verify that no institution is added
        assertEquals(setOf(student1), students.getAll().toSet())         // verify that only one student is added
        assertEquals(student1, students.getOne(student1.id))               // verify that we can access student by id
        assertEquals(setOf(student1), institutions.getStudentsFromInstitution(institutions.getOne(institution1.id)).toSet())  // verify that student is added to institution
        assertEquals(institution1, students.getOne(student1.id).institution) // verify that institution is added to student
    }

    @Test
    fun `add one application to Grant Call test`() {
        `add Student test`() // must be able to add students (and thus institutions)
        `add Grant Call test`() // must be able to create Grant Calls

        app1 = ApplicationDAO(0, Date(), 0, calls.getOne(grantCall1.id), mutableSetOf(), students.getOne(student1.id), mutableSetOf())

        calls.addApplication(app1)

        grantCall1.applications.add(app1)
        student1.applications.add(app1)
        app1.grantCall = grantCall1
        app1.student = student1

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
        val editedCall = GrantCallDAO(89, "edited title", "edited description", 100.0, Date(), Date(), mutableSetOf(), null, mutableSetOf(), SponsorDAO())

        calls.editCall(calls.getOne(grantCall1.id), editedCall)

        grantCall1.title = editedCall.title
        grantCall1.description = editedCall.description
        grantCall1.funding = editedCall.funding
        grantCall1.openDate = editedCall.openDate
        grantCall1.closeDate = editedCall.closeDate

        assertEquals(grantCall1, calls.getOne(grantCall1.id))    //verifies that attributes have been changed and relations kept
    }

    @Test
    fun `add second Grant Call test`() {
        `edit Grant Call test`()

        grantCall2 = GrantCallDAO(0, "Second Grant Call", "Second description", 40.0, Date(), Date(), mutableSetOf(), null, mutableSetOf(), sponsors.getOne(sponsor1.id))

        sponsors.addGrantCall(sponsors.getOne(sponsor1.id), grantCall2)

        sponsor1.grantCalls.add(grantCall2)
        grantCall2.sponsor = sponsor1

        assertEquals(setOf(grantCall1, grantCall2), calls.getAll().toSet())
        assertEquals(setOf(sponsor1), sponsors.getAll().toSet())
        assertEquals(sponsor1, sponsors.getOne(sponsor1.id))
        assertEquals(grantCall2, calls.getOne(grantCall2.id))
        assertEquals(sponsor1, calls.getOne(grantCall2.id).sponsor)
        assertEquals(setOf(grantCall1, grantCall2), sponsors.getGrantCallsFromSponsor(sponsors.getOne(sponsor1.id)).toSet())
    }

    @Test
    fun `delete second Grant call test`() {
        `add second Grant Call test`()

        assertEquals(setOf(grantCall1, grantCall2), calls.getAll().toSet())

        calls.deleteCall(calls.getOne(grantCall2.id))

        sponsor1.grantCalls.remove(grantCall2)

        assertEquals(setOf(grantCall1), calls.getAll().toSet())
        assertEquals(setOf(sponsor1), sponsors.getAll().toSet())
        assertEquals(sponsor1, sponsors.getOne(sponsor1.id))
        assertEquals(grantCall1, calls.getOne(grantCall1.id))
        assertEquals(sponsor1, calls.getOne(grantCall1.id).sponsor)
        assertEquals(setOf(grantCall1), sponsors.getGrantCallsFromSponsor(sponsors.getOne(sponsor1.id)).toSet())
    }

    @Test
    fun `add third Grant Call test`() {
        `delete second Grant call test`()

        grantCall3 = GrantCallDAO(0, "Third Grant Call", "Third description", 60.0, Date(), Date(), mutableSetOf(), null, mutableSetOf(), sponsors.getOne(sponsor1.id))

        sponsors.addGrantCall(sponsors.getOne(sponsor1.id), grantCall3)

        sponsor1.grantCalls.add(grantCall3)
        grantCall3.sponsor = sponsor1

        assertEquals(setOf(grantCall1, grantCall3), calls.getAll().toSet())
        assertEquals(setOf(sponsor1), sponsors.getAll().toSet())
        assertEquals(sponsor1, sponsors.getOne(sponsor1.id))
        assertEquals(grantCall3, calls.getOne(grantCall3.id))
        assertEquals(sponsor1, calls.getOne(grantCall3.id).sponsor)
        assertEquals(setOf(grantCall1, grantCall3), sponsors.getGrantCallsFromSponsor(sponsors.getOne(sponsor1.id)).toSet())
    }


    @Test
    fun `add second application to Grant Call test`() {
        `add third Grant Call test`()

        app2 = ApplicationDAO(0, Date(), 1, calls.getOne(grantCall1.id), mutableSetOf(), students.getOne(student1.id), mutableSetOf())

        calls.addApplication(app2)

        grantCall1.applications.add(app2)
        student1.applications.add(app2)
        app2.grantCall = grantCall1
        app2.student = student1

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

        app3 = ApplicationDAO(0, Date(), 1, calls.getOne(grantCall1.id), mutableSetOf(), students.getOne(student1.id), mutableSetOf())
        calls.addApplication(app3)

        grantCall1.applications.add(app3)
        student1.applications.add(app3)
        app3.grantCall = grantCall1
        app3.student = student1

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

        calls.deleteApplication(calls.getOne(grantCall1.id), applications.getOne(app3.id))

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
        `remove application test`()

        assertEquals(setOf(app1, app2), calls.getCallApplications(calls.getOne(grantCall1.id)))
    }

    @Test
    fun `basic get(empty)PanelFromGrantCall test`() {
        `getCallApplications with two applications test`()

        val panel = calls.getPanelFromGrantCall(calls.getOne(grantCall3.id))

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

        reviewer1 = ReviewerDAO(0, "reviewer1", "reviewer1@email.com", "reviewer1.address", mutableSetOf(), mutableSetOf(), institutions.getOne(institution1.id), mutableSetOf())

        reviewers.addReviewer(reviewer1)

        reviewer1.institution = institution1
        institution1.reviewers.add(reviewer1)

        assertEquals(setOf(reviewer1), reviewers.getAll().toSet())
        assertEquals(reviewer1, reviewers.getOne(reviewer1.id))
        assertEquals(setOf(institution1), institutions.getAll().toSet())
        assertEquals(setOf(reviewer1), institutions.getReviewersFromInstitution(institutions.getOne(institution1.id)))
    }

    @Test
    fun `add reviewer to panel test`() {
        `add reviewer test`()

        assertEquals(grantCall3, calls.getOne(grantCall3.id))

        calls.addReviewerToPanel(calls.getPanelFromGrantCall(calls.getOne(grantCall3.id)), reviewers.getOne(reviewer1.id))

        val panel = calls.getPanelFromGrantCall(calls.getOne(grantCall3.id))
        reviewer1.panels.add(panel)
        grantCall3.panel = panel;

        assertEquals(setOf(reviewer1), reviewers.getAll().toSet()) // verify that reviewer was added
        assertEquals(reviewer1, reviewers.getOne(reviewer1.id))  // verify get reviewer by id
        assertEquals(setOf(reviewer1), calls.getReviewers(calls.getPanelFromGrantCall(calls.getOne(grantCall3.id)))) // verify that reviewer was added to right panel
        assertEquals(setOf(calls.getPanelFromGrantCall(calls.getOne(grantCall3.id))), reviewers.getOne(reviewer1.id).panels)

    }

    @Test
    fun `delete reviewer from panel test`() {
        `add reviewer to panel test`()

        assertEquals(grantCall3, calls.getOne(grantCall3.id))

        calls.deleteReviewerFromPanel(calls.getPanelFromGrantCall(calls.getOne(grantCall3.id)), reviewers.getOne(reviewer1.id))

        reviewer1.panels = mutableSetOf()
        grantCall3.panel?.reviewers = mutableSetOf()


        assertEquals(setOf(reviewer1), reviewers.getAll().toSet()) // verify that reviewer still exists
        assertEquals(reviewer1, reviewers.getOne(reviewer1.id))  // verify get reviewer by id
        assertEquals(emptySet<ReviewerDAO>(), calls.getReviewers(calls.getPanelFromGrantCall(calls.getOne(grantCall3.id)))) // verify that reviewer was deleted
        assertEquals(setOf(grantCall1, grantCall3), calls.getAll().toSet()) // verify that no other changes have been made
    }

    @Test
    fun `get empty dataItems from grant call test`() {
        `delete reviewer from panel test`()

        assertEquals(emptyList<DataItemDAO>(), calls.getAllDataItems(calls.getOne(grantCall1.id)).toList())
    }

    @Test
    fun `add dataItem test`() {
        `get empty dataItems from grant call test`()

        dataItem1 = DataItemDAO(0, "dataItem1", "Long", true, mutableSetOf(), mutableSetOf())

        dataItems.addDataItem(dataItem1)

        assertEquals(setOf(dataItem1), dataItems.getAll().toSet())
        assertEquals(dataItem1, dataItems.getOne(dataItem1.id))
    }

    @Test
    fun `add second dataItem test`() {
        `add dataItem test`()

        dataItem2 = DataItemDAO(0, "dataItem2", "String", false, mutableSetOf(), mutableSetOf())

        dataItems.addDataItem(dataItem2)

        assertEquals(setOf(dataItem1, dataItem2), dataItems.getAll().toSet())
        assertEquals(dataItem2, dataItems.getOne(dataItem2.id))
    }

    @Test
    fun `add dataItem to grant call test`() {
        `add second dataItem test`()

        calls.addDataItem(calls.getOne(grantCall1.id), dataItems.getOne(dataItem1.id))

        dataItem1.grantCalls.add(grantCall1)
        grantCall1.dataItems.add(dataItem1)

        assertEquals(setOf(grantCall1, grantCall3), calls.getAll().toSet()) // verify that grant calls are correct
        assertEquals(setOf(dataItem1), calls.getAllDataItems(calls.getOne(grantCall1.id))) // verifiy getalldataitems
        assertEquals(dataItem1, calls.getOneDataItem(calls.getOne(grantCall1.id), dataItem1.id)) // verify getonedataItem
        assertEquals(dataItem1.grantCalls, calls.getOneDataItem(calls.getOne(grantCall1.id), dataItem1.id).grantCalls) // verify that grant call was added to dataitem
    }

    @Test
    fun `add second dataItem to grant call test`() {
        `add dataItem to grant call test`()

        calls.addDataItem(calls.getOne(grantCall1.id), dataItems.getOne(dataItem2.id))

        dataItem2.grantCalls.add(grantCall1)
        grantCall1.dataItems.add(dataItem2)

        assertEquals(setOf(grantCall1, grantCall3), calls.getAll().toSet()) // verify that grant calls are correct
        assertEquals(setOf(dataItem1, dataItem2), calls.getAllDataItems(calls.getOne(grantCall1.id))) // verifiy getalldataitems
        assertEquals(dataItem2, calls.getOneDataItem(calls.getOne(grantCall1.id), dataItem2.id)) // verify getonedataItem
        assertEquals(dataItem2.grantCalls, calls.getOneDataItem(calls.getOne(grantCall1.id), dataItem1.id).grantCalls) // verify that grant call was added to dataitem
    }

    @Test
    fun `delete second DataItem from grant call test`() {
        `add second dataItem to grant call test`()

        assertEquals(dataItem2, calls.getOneDataItem(calls.getOne(grantCall1.id), dataItem2.id))

        calls.deleteDataItem(calls.getOne(grantCall1.id), calls.getOneDataItem(calls.getOne(grantCall1.id), dataItem2.id))

        grantCall1.dataItems.remove(dataItem2)
        dataItem2.grantCalls.remove(grantCall1)

        assertEquals(setOf(grantCall1, grantCall3), calls.getAll().toSet()) // verify that grant calls are correct
        assertEquals(setOf(dataItem1), calls.getAllDataItems(calls.getOne(grantCall1.id))) // verify that data item was removed from grant call
        assertEquals(setOf(dataItem1, dataItem2), dataItems.getAll().toSet()) // verify that data items are correct
    }

}
