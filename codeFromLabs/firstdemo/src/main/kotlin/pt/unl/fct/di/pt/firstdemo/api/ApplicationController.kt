package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.ApplicationService
import java.util.*

@RestController
class ApplicationController(val applications: ApplicationService): ApplicationAPI {

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

    override fun addAnswer(id:Long) = applications.addAnswer(id)

    override fun editAnswer(id:Long, name: String) = applications.editAnswer(id, name)

    override fun deleteAnswer(id:Long, name: String) = applications.deleteAnswer(id, name)
}
