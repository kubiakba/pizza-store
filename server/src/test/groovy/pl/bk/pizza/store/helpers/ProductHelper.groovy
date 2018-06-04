package pl.bk.pizza.store.helpers

import org.springframework.test.web.reactive.server.WebTestClient
import pl.bk.pizza.store.application.dto.order.OrderDTO
import pl.bk.pizza.store.application.dto.product.input.NewProductDTO
import pl.bk.pizza.store.application.dto.product.input.NewProductPriceDTO
import pl.bk.pizza.store.application.dto.product.output.PizzaDTO
import pl.bk.pizza.store.application.dto.product.output.ProductDTO
import pl.bk.pizza.store.infrastructure.error.ErrorMessage
import reactor.core.publisher.Mono

import static org.springframework.http.HttpMethod.GET

trait ProductHelper
{
    abstract WebTestClient getClient()

    ProductDTO createProduct(NewProductDTO product)
    {
        client
            .post()
            .uri("/products")
            .body(Mono.just(product), NewProductDTO.class)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(ProductDTO)
            .returnResult()
            .responseBody
    }

    ErrorMessage createProductWithError(NewProductDTO product)
    {
        client
            .post()
            .uri("/products")
            .body(Mono.just(product), NewProductDTO.class)
            .exchange()
            .expectBody(ErrorMessage)
            .returnResult()
            .responseBody
    }

    ProductDTO getProduct(String id)
    {
        client
            .method(GET)
            .uri("/products/$id")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(ProductDTO)
            .returnResult()
            .responseBody
    }

    OrderDTO addProductToOrder(String orderId, String productId)
    {
        client
            .put()
            .uri("/orders/$orderId/$productId")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(OrderDTO)
            .returnResult()
            .responseBody
    }

    ProductDTO makeProductNonAvailable(String id)
    {
        client
            .put()
            .uri("/products/$id/non-available")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(ProductDTO)
            .returnResult()
            .responseBody
    }

    ProductDTO changeProductPrice(String id, NewProductPriceDTO productPriceDTO)
    {
        client
            .put()
            .uri("/products/$id/changePrice")
            .body(Mono.just(productPriceDTO), NewProductPriceDTO)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(ProductDTO)
            .returnResult()
            .responseBody
    }

    List<PizzaDTO> getPizzas()
    {
        client
            .method(GET)
            .uri("/products/pizzas")
            .exchange()
            .expectBodyList(PizzaDTO)
            .returnResult()
            .responseBody
    }
}
