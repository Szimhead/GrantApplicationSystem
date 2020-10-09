package pt.unl.fct.di.pt.grantManagementApplication.api

import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.grantManagementApplication.services.ApplicationsService

@Api(value="Applications", description = "Operation management of Applications", tags=["Applications"])
@RestController
class ApplicationsController(val applications: ApplicationsService): ApplicationsAPI {

    override fun getAll() = applications.getAll()

    override fun getOne(id:Long) = applications.getOne(id)

    override fun deleteApplication(id: Long) = applications.deleteApplication(id)

    override fun editApplication(id:Long) = applications.editApplication(id)

    override fun getAllReviewsFromApplication(id:Long) = applications.getAllReviewsFromApplication(id)

    override fun getOneReview(id:Long, review_id:Long) = applications.getOneReview(id, review_id)

    override fun deleteReview(id:Long, review_id: Long) = applications.deleteReview(id, review_id)

    override fun editReview(id:Long, review_id: Long) = applications.editReview(id, review_id)

    override fun addReview(id:Long, review_id:Long) = applications.addReview(id, review_id)

    override fun getAllAnswers(id:Long) = applications.getAllAnswers(id)

    override fun getOneAnswer(id:Long, name: String) = applications.getOneAnswer(id, name)

    override fun addAnswer(id: Long, name: String) = applications.addAnswer(id, name)

    override fun editAnswer(id:Long, name: String) = applications.editAnswer(id, name)

    override fun deleteAnswer(id:Long, name: String) = applications.deleteAnswer(id, name)
}
