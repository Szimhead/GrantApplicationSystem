package pt.unl.fct.di.pt.firstdemo.api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chair")
class ChairController(val chairs:ChairService):ChairAPI {
    override fun getAll() {
        TODO("Not yet implemented")
    }

    override fun getOne(id: Long) {
        TODO("Not yet implemented")
    }

    override fun addChair(final: Long) {
        TODO("Not yet implemented")
    }

    override fun editChair(id: Long) {
        TODO("Not yet implemented")
    }

    override fun deleteChair(id: Long) {
        TODO("Not yet implemented")
    }

}