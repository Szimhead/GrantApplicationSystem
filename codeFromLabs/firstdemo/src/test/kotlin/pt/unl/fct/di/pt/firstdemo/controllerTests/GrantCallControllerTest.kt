package pt.unl.fct.di.pt.firstdemo.controllerTests

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import pt.unl.fct.di.pt.firstdemo.api.*
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.services.*
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class GrantCallControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var calls: GrantCallService

    @MockBean
    lateinit var studs: StudentService

    @MockBean
    lateinit var apps: ApplicationService

    @MockBean
    lateinit var revs: ReviewerService

    @MockBean
    lateinit var sponsors: SponsorService

    companion object {
        val mapper = ObjectMapper().registerModule(KotlinModule())

        val sponsor = SponsorDAO(0, "sponsor1", "sponsor1 contact", mutableSetOf())

        val grantCall1 = GrantCallDAO(0, "Grant Call", "some description", 20.0, Date(), Date(), mutableSetOf(), null, mutableSetOf(), sponsor)
        var grantCall2 = GrantCallDAO(0, "Second Grant Call", "Second description", 40.0, Date(), Date(), mutableSetOf(), null, mutableSetOf(), sponsor)
        var grantCall3 = GrantCallDAO(0, "Third Grant Call", "Third description", 60.0, Date(), Date(), mutableSetOf(), null, mutableSetOf(), sponsor)


        val panelDAO = PanelDAO(0, null, mutableSetOf(), grantCall1)
        val panelDTO = PanelDTO(panelDAO)

        val callsDAO = setOf(grantCall1, grantCall2, grantCall3)
        val callsDTO = callsDAO.map { GrantCallDTO(it) }.toSet()

        val institution1 = InstitutionDAO(0, "first institution", "first_inst_contact", mutableSetOf(), mutableSetOf())

        val student1 = StudentDAO(0, "Tiago", "tiago@email.com", "tiago's street n2", mutableSetOf(), institution1, null)

        val reviewer1 = ReviewerDAO(0, "reviewer1", "reviewer1@email", "reviewer1.address", mutableSetOf(), mutableSetOf(panelDAO), institution1, mutableSetOf())
        val reviewer2 = ReviewerDAO(0, "reviewer2", "reviewer2@email", "reviewer2.address", mutableSetOf(), mutableSetOf(panelDAO), institution1, mutableSetOf())


        val reviewersDAO = setOf(reviewer1, reviewer2)
        var reviewersDTO = reviewersDAO.map{ UserDTO(it) }.toSet()


        val app1 = ApplicationDAO(0, Date(), 0, grantCall1, mutableSetOf(), student1, mutableSetOf())
        val app2 = ApplicationDAO(0, Date(), 1, grantCall1, mutableSetOf(), student1, mutableSetOf())

        val appsDAO = setOf(app1, app2)
        val appsDTO = appsDAO.map { ApplicationDTO(it) }.toSet()

        val dataItem1 = DataItemDAO(0, "dataitem1", "datatype1", true, mutableSetOf(grantCall1), mutableSetOf())
        val dataItem2 = DataItemDAO(0, "dataitem2", "datatype2", false,  mutableSetOf(grantCall1), mutableSetOf())

        val dataItemsDAO = setOf(dataItem1, dataItem2)
        val dataItemsDTO = dataItemsDAO.map { DataItemDTO(it) }.toSet()


        const val callsURL = "/calls"

    }

    fun <T>nonNullAny(t:Class<T>):T = Mockito.any(t)

    @Test
    fun `empty getAll grantCalls test`() {
        Mockito.`when`(calls.getAll()).thenReturn(emptyList())

        mvc.perform(get(callsURL))
                .andExpect(status().isOk)
    }

    @Test
    fun `getAll grantCalls test`() {
        Mockito.`when`(calls.getAll()).thenReturn(callsDAO)

        val result = mvc.perform(get(callsURL))
                .andExpect(status().isOk)
                .andReturn()

        val responseString = result.response.contentAsString
        val responseSet = mapper.readValue<Set<GrantCallDTO>>(responseString)

        assertEquals(callsDTO, responseSet)
    }

    @Test
    fun `get one grant call test`() {
        val id = grantCall1.id
        Mockito.`when`(calls.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            return@then grantCall1
        }

        val result = mvc.perform(get("$callsURL/$id"))
                .andExpect(status().isOk)
                .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<GrantCallDTO>(responseString)

        assertEquals(responseDTO, GrantCallDTO(grantCall1))
    }

    @Test
    fun `get one grant call test(not found)`() {
        val id = 2
        Mockito.`when`(calls.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            throw NotFoundException("Grant Call with id 2 not found")
        }

        mvc.perform(get("$callsURL/$id"))
                .andExpect(status().is4xxClientError)
    }

    @Test
    fun `add grant call test`() {
        val sponsorId = 2L
        val grantCall = GrantCallDTO(0, "title_add", "description_add", 10.0, Date(), Date(), sponsorId)
        val grantCallDAO = GrantCallDAO(grantCall)

        val grantCallJSON = mapper.writeValueAsString(grantCall)

        Mockito.`when`(sponsors.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(sponsorId, it.getArgument(0))
        }

        Mockito.`when`(calls.addCall(nonNullAny(GrantCallDAO::class.java)))
                .then { assertEquals(it.getArgument(0), grantCallDAO) }

        mvc.perform(post(callsURL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(grantCallJSON))
                .andExpect(status().isOk)
    }

     @Test
     fun `edit grant call test`() {
         val sponsorId = 2L
         val grantCall = GrantCallDTO(0, "title_edit", "description_edit", 10.0, Date(), Date(), sponsorId)
         val grantCallDAO = GrantCallDAO(grantCall)
         val id = 1L
         val grantCallJSON = mapper.writeValueAsString(grantCall)

         val grantCallToEdit = GrantCallDAO()

         Mockito.`when`(calls.getOne(nonNullAny(Long::class.java))).then {
             assertEquals(id, it.getArgument(0))
             return@then grantCallToEdit
         }

         Mockito.`when`(calls.editCall(nonNullAny(GrantCallDAO::class.java), nonNullAny(GrantCallDAO::class.java)))
                 .then {
                     assertEquals(grantCallToEdit, it.getArgument(0))
                     assertEquals(grantCallDAO, it.getArgument(1))
                 }


         mvc.perform(put("$callsURL/$id")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(grantCallJSON))
                 .andExpect(status().isOk)
     }


    @Test
    fun `delete grant call test`() {
        val id = 1L

        val grantCallToDelete = GrantCallDAO()

        Mockito.`when`(calls.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            return@then grantCallToDelete
        }

        Mockito.`when`(calls.deleteCall(nonNullAny(GrantCallDAO::class.java))).then {
            assertEquals(it.getArgument(0), grantCallToDelete)
        }

        mvc.perform(delete("$callsURL/$id"))
                .andExpect(status().isOk)
    }

    @Test
    fun `get all applications from grant call test`() {
        val id = 1L
        val grantCall = GrantCallDAO()

        Mockito.`when`(calls.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            return@then grantCall
        }

        Mockito.`when`(calls.getCallApplications(nonNullAny(GrantCallDAO::class.java))).then {
            assertEquals(it.getArgument(0), grantCall)
            return@then appsDAO
        }

            val result = mvc.perform(get("$callsURL/$id/applications"))
                    .andExpect(status().isOk)
                    .andReturn()

            val responseString = result.response.contentAsString
            val responseSet = mapper.readValue<Set<ApplicationDTO>>(responseString)

            assertEquals(appsDTO, responseSet)
    }

    @Test
    fun `add application to grant call test`() {
        val id = 1L
        val grantCall = GrantCallDAO()

        val applicationDAO = ApplicationDAO(0, Date(), 0, grantCall, mutableSetOf(), student1, mutableSetOf())
        val applicationDTO = ApplicationDTO(applicationDAO)
        val applicationJSON = mapper.writeValueAsString(applicationDTO)


        Mockito.`when`(calls.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            return@then grantCall
        }

        Mockito.`when`(studs.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(student1.id, it.getArgument(0))
            return@then student1
        }
        Mockito.`when`(calls.addApplication(nonNullAny(ApplicationDAO::class.java)))
                .then {
                    assertEquals(applicationDAO, it.getArgument(0))
                }

        mvc.perform(post("$callsURL/$id/applications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(applicationJSON))
                .andExpect(status().isOk)
    }

    @Test
    fun `delete application from grant call test`() {
        val id = 1L
        val grantCall = GrantCallDAO()

        val appId = 2L
        val app = ApplicationDAO()

        Mockito.`when`(calls.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            return@then grantCall
        }

        Mockito.`when`(apps.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(appId, it.getArgument(0))
            return@then app
        }

        Mockito.`when`(calls.deleteApplication(nonNullAny(GrantCallDAO::class.java), nonNullAny(ApplicationDAO::class.java))).then {
            assertEquals(it.getArgument(0), grantCall)
            assertEquals(it.getArgument(1), app)
        }

        mvc.perform(delete("$callsURL/$id/applications/$appId"))
                .andExpect(status().isOk)
    }


    @Test
    fun `get all reviewers from grant call panel test`() {
        val id = grantCall1.id
        panelDAO.reviewers.add(reviewer1)
        panelDAO.reviewers.add(reviewer2)

        Mockito.`when`(calls.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            return@then grantCall1
        }

        Mockito.`when`(calls.getPanelFromGrantCall(nonNullAny(GrantCallDAO::class.java))).then {
            assertEquals(grantCall1, it.getArgument(0))

            return@then panelDAO;
        }

        Mockito.`when`(calls.getReviewers(nonNullAny(PanelDAO::class.java))).then {
            assertEquals(panelDAO, it.getArgument(0))

            return@then reviewersDAO
        }

        val result = mvc.perform(get("$callsURL/$id/panel/reviewers"))
                .andExpect(status().isOk)
                .andReturn()

        val responseString = result.response.contentAsString
        val responseSet = mapper.readValue<Set<UserDTO>>(responseString)

        assertEquals(reviewersDTO, responseSet)
    }

    @Test
    fun `add reviewer to panel from grant call test`() {
        val id = grantCall1.id
        val reviewerId = reviewer1.id

        Mockito.`when`(calls.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            return@then grantCall1
        }

        Mockito.`when`(calls.getPanelFromGrantCall(nonNullAny(GrantCallDAO::class.java))).then {
            assertEquals(grantCall1, it.getArgument(0))

            return@then panelDAO;
        }

        Mockito.`when`(revs.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(reviewerId, it.getArgument(0))
            return@then reviewer1
        }

        Mockito.`when`(calls.addReviewerToPanel(nonNullAny(PanelDAO::class.java), nonNullAny(ReviewerDAO::class.java))).then {
            assertEquals(panelDAO, it.getArgument(0))
            assertEquals(reviewer1, it.getArgument(1))
        }

        mvc.perform(post("$callsURL/$id/panel/reviewers/$reviewerId"))
                .andExpect(status().isOk)

    }

    @Test
    fun `delete reviewer from grant call panel test`() {
        val id = grantCall1.id
        val reviewerId = reviewer1.id

        Mockito.`when`(calls.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            return@then grantCall1
        }

        Mockito.`when`(calls.getPanelFromGrantCall(nonNullAny(GrantCallDAO::class.java))).then {
            assertEquals(grantCall1, it.getArgument(0))

            return@then panelDAO;
        }

        Mockito.`when`(revs.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(reviewerId, it.getArgument(0))
            return@then reviewer1
        }

        Mockito.`when`(calls.deleteReviewerFromPanel(nonNullAny(PanelDAO::class.java), nonNullAny(ReviewerDAO::class.java))).then {
            assertEquals(panelDAO, it.getArgument(0))
            assertEquals(reviewer1, it.getArgument(1))
        }

        mvc.perform(delete("$callsURL/$id/panel/reviewers/$reviewerId"))
                .andExpect(status().isOk)
    }


    @Test
    fun `get all data items from grant call test`() {
        val id = grantCall1.id

        Mockito.`when`(calls.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            return@then grantCall1
        }

        Mockito.`when`(calls.getAllDataItems(nonNullAny(GrantCallDAO::class.java))).then {
            assertEquals(grantCall1, it.getArgument(0))
            return@then dataItemsDAO;
        }

        val result = mvc.perform(get("$callsURL/$id/dataitems"))
                .andExpect(status().isOk)
                .andReturn()

        val responseString = result.response.contentAsString
        val responseSet = mapper.readValue<Set<DataItemDTO>>(responseString)

        assertEquals(dataItemsDTO, responseSet)
    }

    @Test
    fun `get one data item from grant call test`() {
        val id = grantCall1.id
        val dataItemId = dataItem1.id

        Mockito.`when`(calls.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            return@then grantCall1
        }

        Mockito.`when`(calls.getOneDataItem(nonNullAny(GrantCallDAO::class.java), nonNullAny(Long::class.java))).then {
            assertEquals(grantCall1, it.getArgument(0))
            assertEquals(dataItemId, it.getArgument(1))

            return@then dataItem1
        }

        val result = mvc.perform(get("$callsURL/$id/dataitems/$dataItemId"))
                .andExpect(status().isOk)
                .andReturn()

        val responseString = result.response.contentAsString
        val responseDataItem = mapper.readValue<DataItemDTO>(responseString)

        assertEquals(DataItemDTO(dataItem1), responseDataItem)
    }

    @Test
    fun `add data item to grant call test`() {
        val id = grantCall1.id
        val dataItemDAO = DataItemDAO(0, "data item1", "type data1", true, mutableSetOf(), mutableSetOf())
        val dataItemDTO = DataItemDTO(dataItemDAO)

        val dataItemJSON = mapper.writeValueAsString(dataItemDTO)

        Mockito.`when`(calls.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            return@then grantCall1
        }

        Mockito.`when`(calls.addDataItem(nonNullAny(GrantCallDAO::class.java), nonNullAny(DataItemDAO::class.java)))
                .then {
                    assertEquals(grantCall1, it.getArgument(0))
                    assertEquals(dataItemDAO, it.getArgument(1))
                }

        mvc.perform(post("$callsURL/$id/dataitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dataItemJSON))
                .andExpect(status().isOk)
    }

    @Test
    fun `delete data item from grant call test`() { //TODO
        val id = grantCall1.id
        val dataItemId = dataItem1.id

        Mockito.`when`(calls.getOne(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            return@then grantCall1
        }

        Mockito.`when`(calls.getOneDataItem(nonNullAny(GrantCallDAO::class.java), nonNullAny(Long::class.java))).then {
            assertEquals(grantCall1, it.getArgument(0))
            assertEquals(dataItemId, it.getArgument(1))
            return@then dataItem1
        }

        Mockito.`when`(calls.deleteDataItem(nonNullAny(GrantCallDAO::class.java), nonNullAny(DataItemDAO::class.java))).then {
            assertEquals(grantCall1, it.getArgument(0))
            assertEquals(dataItem1, it.getArgument(1))
        }

        mvc.perform(delete("$callsURL/$id/dataitems/$dataItemId"))
                .andExpect(status().isOk)
    }

}