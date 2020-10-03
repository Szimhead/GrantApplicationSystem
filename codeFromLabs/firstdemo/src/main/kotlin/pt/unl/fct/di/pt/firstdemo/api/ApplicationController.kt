package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.di.pt.firstdemo.services.ApplicationService
import java.util.*

@RestController
class ApplicationController(val applications: ApplicationService): ApplicationAPI {

    override fun getAll() = applications.getAll()

    override fun getOne(id:Long) = applications.getOne(id)

    override fun getAllReviews(id: Long) = applications.getAllReviews()

    override fun getReview(app_id: Long, review_id: Long) = applications.getReview(app_id, review_id)

    override fun getFinal(id: Long) = applications.getFinal()
}
