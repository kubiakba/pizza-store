package pl.bk.pizza.store.helpers

import org.springframework.test.web.reactive.server.WebTestClient
import pl.bk.pizza.store.application.dto.order.NewOrderDTO
import pl.bk.pizza.store.application.dto.order.OrderDTO
import pl.bk.pizza.store.infrastructure.error.ErrorMessage
import reactor.core.publisher.Mono

trait OrderHelper
{
    abstract WebTestClient getClient()

    OrderDTO createOrder(String email)
    {
        client
            .post()
            .uri("/orders")
            .body(Mono.just(new NewOrderDTO(email)), NewOrderDTO.class)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(OrderDTO)
            .returnResult()
            .responseBody
    }

    ErrorMessage createOrderWithError(String email)
    {
        client
            .post()
            .uri("/orders")
            .body(Mono.just(new NewOrderDTO(email)), NewOrderDTO.class)
            .exchange()
            .expectBody(ErrorMessage)
            .returnResult()
            .responseBody
    }

    OrderDTO startOrderRealization(String id)
    {
        client
            .put()
            .uri("/orders/$id/to-realization")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(OrderDTO)
            .returnResult()
            .responseBody
    }

    OrderDTO deliverOrder(String id)
    {
        client
            .put()
            .uri("/orders/$id/delivered")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(OrderDTO)
            .returnResult()
            .responseBody
    }
}
