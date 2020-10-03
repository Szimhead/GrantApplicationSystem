package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.di.pt.firstdemo.services.ChairService

@RestController
@RequestMapping("/chair")
class ChairController(val chairs: ChairService):ChairAPI {
    override fun getAll() = chairs.getAll()

    override fun getOne(id: Long) = chairs.getOne(id)

    override fun addChair(id: Long) = chairs.addChair(id)

    override fun editChair(id: Long) =chairs.editChair(id)

    override fun deleteChair(id: Long) = chairs.deleteChair(id)

}