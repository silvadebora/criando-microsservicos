package br.com.erudio.configuration

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiGatewayConfiguration {

    // redirecionamento
    @Bean
    fun gatewayRouter(builder: RouteLocatorBuilder): RouteLocator{
        return builder.routes()
            .route{p: PredicateSpec ->
                p.path("/get")
                    .filters{f: GatewayFilterSpec ->
                        f.addRequestHeader("Helllo", "World")
                        f.addRequestParameter("Helllo", "World")
                    }
                    .uri("http://httpbin.org:80")
            }
            .route{p: PredicateSpec ->
                p.path("/cambio-service/**").uri("lb://cambio-service/")
            }
            .route{p: PredicateSpec ->
                p.path("/book-service/**").uri("lb://book-service/")
            }
            .build()
    }



}