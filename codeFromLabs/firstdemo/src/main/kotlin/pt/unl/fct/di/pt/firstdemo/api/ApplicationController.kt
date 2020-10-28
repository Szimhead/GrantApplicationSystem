package pt.unl.fct.di.pt.firstdemo.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.pt.firstdemo.services.AnswerDAO
import pt.unl.fct.di.pt.firstdemo.services.ApplicationDAO
import pt.unl.fct.di.pt.firstdemo.services.ApplicationService
import pt.unl.fct.di.pt.firstdemo.services.ReviewDAO
import java.util.*

@RestController
class ApplicationController(val applications: ApplicationService): ApplicationAPI {

    override fun getAll() = applications.getAll().map { ApplicationDTO(it) }

    override fun getOne(id:Long) = ApplicationDTO(applications.getOne(id))

    override fun editApplication(id:Long, app: ApplicationDTO) = applications.editApplication(id, ApplicationDAO(app))

    override fun getAllReviewsFromApplication(id:Long) = applications.getAllReviewsFromApplication(id).map { ReviewDTO(it) }

    override fun getOneReview(id:Long, review_id:Long) = ReviewDTO(0, true,"", 0)//TODO: applications.getOneReview(id, review_id)

    override fun deleteReview(id:Long, reviewId: Long) = applications.deleteReview(id, reviewId)

    override fun editReview(id:Long, review:ReviewDTO) = applications.editReview(id, review)

    override fun addReview(id:Long, review:ReviewDTO) = applications.addReview(id, review.reviewerId, ReviewDAO(review))

    override fun getAllAnswers(id:Long) = applications.getAllAnswers(id).map { AnswerDTO(it) }

    override fun getOneAnswer(id:Long, answerId:Long) = AnswerDTO(applications.getOneAnswer(id, answerId))

    override fun addAnswer(id: Long, answer:AnswerDTO) = applications.addAnswer(id, AnswerDAO(answer))

    override fun editAnswer(id:Long, answer:AnswerDTO) = applications.editAnswer(id, AnswerDAO(answer))

    override fun deleteAnswer(id:Long, answerId:Long) = applications.deleteAnswer(id, answerId)
}
