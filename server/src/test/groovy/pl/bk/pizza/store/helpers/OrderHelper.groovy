package pl.bk.pizza.store.helpers

import org.springframework.test.web.reactive.server.WebTestClient
import pl.bk.common.dto.order.DeliveryInfoDTO
import pl.bk.common.dto.order.NewOrderDTO
import pl.bk.common.dto.order.OrderDTO
import pl.bk.common.dto.order.discount.DiscountDTO
import pl.bk.pizza.store.infrastructure.error.ErrorMessage
import reactor.core.publisher.Mono

import static org.springframework.http.HttpMethod.GET

trait OrderHelper
{
    abstract WebTestClient getClient()

    OrderDTO createOrder(String email, DeliveryInfoDTO deliveryInfoDTO)
    {
        client
            .post()
            .uri("/orders")
            .body(Mono.just(new NewOrderDTO(email, deliveryInfoDTO)), NewOrderDTO.class)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(OrderDTO)
            .returnResult()
            .responseBody
    }

    OrderDTO addDiscountToOrder(String orderId, DiscountDTO discountDTO)
    {
        client
            .put()
            .uri("/orders/discounts/$orderId/addDiscount")
            .body(Mono.just(discountDTO),DiscountDTO)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(OrderDTO)
            .returnResult()
            .responseBody
    }

    OrderDTO applyDiscounts(String orderId){
        client
            .put()
            .uri("/orders/discounts/$orderId/apply")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(OrderDTO)
            .returnResult()
            .responseBody
    }

    ErrorMessage createOrderWithError(String email, DeliveryInfoDTO deliveryInfoDTO)
    {
        client
            .post()
            .uri("/orders")
            .body(Mono.just(new NewOrderDTO(email, deliveryInfoDTO)), NewOrderDTO.class)
            .exchange()
            .expectBody(ErrorMessage)
            .returnResult()
            .responseBody
    }

    OrderDTO getOrder(String orderId)
    {
        client
            .method(GET)
            .uri("/orders/${orderId}")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(OrderDTO)
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
            .uri("/admin/orders/$id/delivered")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(OrderDTO)
            .returnResult()
            .responseBody
    }

    ErrorMessage deliverOrderWithError(String id)
    {
        client
            .put()
            .uri("/admin/orders/$id/delivered")
            .exchange()
            .expectBody(ErrorMessage)
            .returnResult()
            .responseBody
    }
}
