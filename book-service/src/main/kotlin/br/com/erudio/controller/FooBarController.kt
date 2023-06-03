package br.com.erudio.controller

import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import io.github.resilience4j.retry.annotation.Retry
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@RestController
@RequestMapping("book-service")
class FooBarController {

    // simulando cenários de falha
    @GetMapping("/foo-bar")
    //@Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
    //retry excede o seu limite e assim entra o método alternativo (fallback)
    //@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod") //impede o colapso completo da aplicação
    //@RateLimiter(name = "default") //determina a quantidade limitada de requisições em um certo periodo tempo
    // a falha ocorre e ele para as chamadas e depois volta
    @Bulkhead(name = "default")
    fun fooBar(): String?{
        /*var response = RestTemplate().getForEntity<String>("http://localhost:8080/foo-bar", String::class)
        return response.body*/



        return "foo-bar"
    }
    // de acordo com a exceção, pode ser tomado diferentes fallbackmethods
    fun fallbackMethod(ex: Exception): String{
        return "fallbackMethod  foo-bar"
    }
}