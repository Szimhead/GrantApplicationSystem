package pt.unl.fct.di.pt.firstdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FirstdemoApplication

fun main(args: Array<String>) {
    runApplication<FirstdemoApplication>(*args)
}
