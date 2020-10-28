package pt.unl.fct.di.pt.firstdemo.controllerTests

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import pt.unl.fct.di.pt.firstdemo.ApplicationAndGrantCallServiceTest
import pt.unl.fct.di.pt.firstdemo.api.*
import pt.unl.fct.di.pt.firstdemo.exceptions.NotFoundException
import pt.unl.fct.di.pt.firstdemo.services.*
import java.util.*
import javax.xml.crypto.Data


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class GrantCallControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var calls: GrantCallService

    companion object {
        val mapper = ObjectMapper().registerModule(KotlinModule())

        val grantCall1 = GrantCallDAO(0, "Grant Call", "some description", 20.0, Date(), Date(), mutableSetOf(), null, mutableSetOf())
        var grantCall2 = GrantCallDAO(0, "Second Grant Call", "Second description", 40.0, Date(), Date(), mutableSetOf(), null, mutableSetOf())
        var grantCall3 = GrantCallDAO(0, "Third Grant Call", "Third description", 60.0, Date(), Date(), mutableSetOf(), null, mutableSetOf())

        val callsDAO = setOf(grantCall1, grantCall2, grantCall3)
        val callsDTO = callsDAO.map { GrantCallDTO(it) }.toSet()

        val institution1 = InstitutionDAO(0, "first institution", "first_inst_contact", mutableSetOf(), mutableSetOf())
        val student1 = StudentDAO(0, "Tiago", "tiago@email.com", "tiago's street n2", mutableSetOf(), institution1, null)

        val app1 = ApplicationDAO(0, Date(), 0, grantCall1, mutableSetOf(), student1, mutableSetOf())
        val app2 = ApplicationDAO(0, Date(), 1, grantCall1, mutableSetOf(), student1, mutableSetOf())

        val appsDAO = setOf(app1, app2)
        val appsDTO = appsDAO.map { ApplicationDTO(it) }.toSet()

        val panelDAO = PanelDAO(0, null, mutableSetOf(), grantCall1)
        val panelDTO = PanelDTO(panelDAO)

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
        val id = 1
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
        val grantCall = GrantCallDTO(0, "title_add", "description_add", 10.0, Date(), Date())
        val grantCallDAO = GrantCallDAO(grantCall)

        val grantCallJSON = mapper.writeValueAsString(grantCall)

        Mockito.`when`(calls.addCall(nonNullAny(GrantCallDAO::class.java)))
                .then { assertEquals(it.getArgument(0), grantCallDAO) }

        mvc.perform(post(callsURL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(grantCallJSON))
                .andExpect(status().isOk)
    }

     @Test
     fun `edit grant call test`() {
         val grantCall = GrantCallDTO(0, "title_edit", "description_edit", 10.0, Date(), Date())
         val grantCallDAO = GrantCallDAO(grantCall)
         val id = 1;
         val grantCallJSON = mapper.writeValueAsString(grantCall)

         Mockito.`when`(calls.editCall(nonNullAny(Long::class.java), nonNullAny(GrantCallDAO::class.java)))
                 .then {
                     assertEquals(it.getArgument(0), id)
                     assertEquals(it.getArgument(1), grantCallDAO)
                 }

         mvc.perform(put("$callsURL/$id")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(grantCallJSON))
                 .andExpect(status().isOk)
     }

    @Test
    fun `edit grant call test(Not Found)`() {
        val grantCall = GrantCallDTO(0, "title_edit", "description_edit", 10.0, Date(), Date())
        val grantCallDAO = GrantCallDAO(grantCall)
        val id = 1;
        val grantCallJSON = mapper.writeValueAsString(grantCall)

        Mockito.`when`(calls.editCall(nonNullAny(Long::class.java), nonNullAny(GrantCallDAO::class.java)))
                .thenThrow( NotFoundException("Grant Call with id $id not found"))

        mvc.perform(put("$callsURL/$id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(grantCallJSON))
                .andExpect(status().is4xxClientError)
    }

    @Test
    fun `delete grant call test`() {
        val id = 1;

        Mockito.`when`(calls.deleteCall(nonNullAny(Long::class.java))).then {
            assertEquals(it.getArgument(0), id)
        }

        mvc.perform(delete("$callsURL/$id"))
                .andExpect(status().isOk)
    }

    @Test
    fun `get all applications from grant call test`() { //TODO: do not found version
        val id = 1

        Mockito.`when`(calls.getCallApplications(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            return@then appsDAO;
        }

        val result = mvc.perform(get("$callsURL/$id/applications"))
                .andExpect(status().isOk)
                .andReturn()

        val responseString = result.response.contentAsString
        val responseSet = mapper.readValue<Set<ApplicationDTO>>(responseString)

        assertEquals(responseSet, appsDTO)
    }

    @Test
    fun `add application to grant call test`() {
        val id = 1
        val applicationDAO = ApplicationDAO(0, Date(), 0, GrantCallDAO(), mutableSetOf(), StudentDAO(), mutableSetOf())
        val applicationDTO = ApplicationDTO(applicationDAO)
        applicationDTO.studentId = student1.id
        val applicationJSON = mapper.writeValueAsString(applicationDTO)

        Mockito.`when`(calls.addApplication(nonNullAny(Long::class.java), nonNullAny(ApplicationDAO::class.java), nonNullAny(Long::class.java)))
                .then {
                    assertEquals(id, it.getArgument(0))
                    assertEquals(applicationDAO, it.getArgument(1))
                    assertEquals(student1.id, it.getArgument(2))
                }

        mvc.perform(post("$callsURL/$id/applications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(applicationJSON))
                .andExpect(status().isOk)
    }

    @Test
    fun `delete application from grant call test`() {
        val id = 1
        val appId = 2

        Mockito.`when`(calls.deleteApplication(nonNullAny(Long::class.java), nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            assertEquals(appId, it.getArgument(1))
        }

        mvc.perform(delete("$callsURL/$id/applications/$appId"))
                .andExpect(status().isOk)
    }

    @Test
    fun `get panel from grant call test (empty panel)`() {
        val id = grantCall1.id

        Mockito.`when`(calls.getPanelFromGrantCall(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))

            return@then panelDAO;
        }

        val result = mvc.perform(get("$callsURL/$id/panel"))
                .andExpect(status().isOk)
                .andReturn()

        val responseString = result.response.contentAsString
        val responsePanel = mapper.readValue<PanelDTO>(responseString)

        assertEquals(responsePanel, panelDTO)
    }

    @Test
    fun `get all reviewers grant call panel test`() {
        val id = grantCall1.id

        var panelDAO = PanelDAO(0, null, mutableSetOf(), grantCall1)
        var reviewerDAO = ReviewerDAO(0, "reviewer1", "reviewer1@email.com", "reviewer1.address", mutableSetOf(), mutableSetOf(panelDAO), institution1, mutableSetOf())
        panelDAO.reviewers.add(reviewerDAO)
        var reviewerSetDAO = setOf(reviewerDAO)

        var reviewerDTO = UserDTO(reviewerDAO)
        var reviewerSetDTO = setOf(reviewerDTO)

        Mockito.`when`(calls.getReviewers(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))

            return@then reviewerSetDAO
        }

        val result = mvc.perform(get("$callsURL/$id/panel/reviewers"))
                .andExpect(status().isOk)
                .andReturn()

        val responseString = result.response.contentAsString
        val responseSet = mapper.readValue<Set<UserDTO>>(responseString)

        assertEquals(reviewerSetDTO, responseSet)
    }

    @Test
    fun `add reviewer to panel from grant call test`() {
        val id = 1
        val reviewerId = 2

        Mockito.`when`(calls.addReviewerToPanel(nonNullAny(Long::class.java), nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            assertEquals(reviewerId, it.getArgument(1))
        }

        mvc.perform(post("$callsURL/$id/panel/reviewers/$reviewerId"))
                .andExpect(status().isOk)

    }

    @Test
    fun `delete reviewer from grant call panel test`() {
        val id = 1
        val reviewerId = 2

        Mockito.`when`(calls.deleteReviewerFromPanel(nonNullAny(Long::class.java), nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            assertEquals(reviewerId, it.getArgument(1))
        }

        mvc.perform(delete("$callsURL/$id/panel/reviewers/$reviewerId"))
                .andExpect(status().isOk)
    }

    @Test
    fun `get all data items from grant call test (empty)`() {
        val id = 1;

        Mockito.`when`(calls.getAllDataItems(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))

            return@then emptyList<DataItemDAO>()
        }

        mvc.perform(get("$callsURL/$id/dataitems"))
                .andExpect(status().isOk)
    }

    @Test
    fun `get all data items from grant call test`() {
        val id = 1

        Mockito.`when`(calls.getAllDataItems(nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
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
        val id = 1
        val dataItemId = dataItem1.id

        Mockito.`when`(calls.getOneDataItem(nonNullAny(Long::class.java), nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
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
        val id = 1
        val dataItemDAO = DataItemDAO(0, "data item1", "type data1", true, mutableSetOf(), mutableSetOf())
        val dataItemDTO = DataItemDTO(dataItemDAO)

        val dataItemJSON = mapper.writeValueAsString(dataItemDTO)

        Mockito.`when`(calls.addDataItem(nonNullAny(Long::class.java), nonNullAny(DataItemDAO::class.java)))
                .then {
                    assertEquals(id, it.getArgument(0))
                    assertEquals(dataItemDAO, it.getArgument(1))
                }

        mvc.perform(post("$callsURL/$id/dataitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dataItemJSON))
                .andExpect(status().isOk)
    }

    @Test
    fun `delete data item from grant call test`() {
        val id = 1
        val dataItemId = 2

        Mockito.`when`(calls.deleteDataItem(nonNullAny(Long::class.java), nonNullAny(Long::class.java))).then {
            assertEquals(id, it.getArgument(0))
            assertEquals(dataItemId, it.getArgument(1))
        }

        mvc.perform(delete("$callsURL/$id/dataitems/$dataItemId"))
                .andExpect(status().isOk)
    }

    @Test
    fun `edit data item from grant call test`() {
        val id = 1;
        val dataItemId = 2
        val editedDataItem = DataItemDTO("edited data item", "edited data type", false)

        val editedDataItemJSON = mapper.writeValueAsString(editedDataItem)

        Mockito.`when`(calls.editDataItem(nonNullAny(Long::class.java), nonNullAny(Long::class.java), nonNullAny(DataItemDAO::class.java))).then {
            assertEquals(id, it.getArgument(0))
            assertEquals(dataItemId, it.getArgument(1))
            assertEquals(DataItemDAO(editedDataItem), it.getArgument(2))
        }

        mvc.perform(put("$callsURL/$id/dataitems/$dataItemId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(editedDataItemJSON))
                .andExpect(status().isOk)
    }

}