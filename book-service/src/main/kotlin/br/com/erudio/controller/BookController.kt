package br.com.erudio.controller

import br.com.erudio.model.Book
import br.com.erudio.model.repository.BookRepository
import br.com.erudio.proxy.CambioProxy
import br.com.erudio.response.Cambio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.util.*
import org.springframework.web.client.getForEntity as getForEntity

@RestController
@RequestMapping("book-service")
class BookController {
    // http://localhost:8100/book-service/1/BRL

    @Autowired
    private lateinit var environment: Environment

    @Autowired
    private lateinit var repository: BookRepository

    @Autowired
    private lateinit var proxy: CambioProxy



    @GetMapping(value = ["/{id}/{currency}"])
    fun findBook(@PathVariable("id") id: Long,
        @PathVariable("currency") currency: String
    ): Book{

        val book = repository.findById(id).orElseThrow{RuntimeException("Book not found")}

        val cambio = proxy.getCambio(book.price, "USD", currency)

        val port = environment.getProperty("local.server.port")

        book.currency = currency
        book.price = cambio!!.convertedValue

        book.environment = "$port FEIGN"

        return book
    }


}

