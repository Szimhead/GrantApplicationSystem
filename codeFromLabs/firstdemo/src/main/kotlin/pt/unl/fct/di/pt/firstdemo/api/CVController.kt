package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.di.pt.firstdemo.services.CVService

@RestController
@RequestMapping("/CV")
class CVController(val CVs: CVService): CVAPI {

    override fun getOne(id: Long) = CVs.getOne(id)

    override fun deleteCV(id: Long) = CVs.deleteCV(id)
}