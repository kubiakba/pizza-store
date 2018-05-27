package pl.bk.pizza.store.helpers

import org.springframework.test.web.reactive.server.WebTestClient
import pl.bk.pizza.store.application.dto.order.OrderDTO
import pl.bk.pizza.store.application.dto.product.input.NewProductDTO
import pl.bk.pizza.store.application.dto.product.input.NewProductPriceDTO
import pl.bk.pizza.store.application.dto.product.output.ProductDTO
import reactor.core.publisher.Mono

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

    OrderDTO addProductToOrder(String orderId, String productId)
    {
        client
            .put()
            .uri("/orders/$orderId/$productId")
            .exchange()
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
            .isNoContent()
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
            .isNoContent()
            .expectBody(ProductDTO)
            .returnResult()
            .responseBody
    }

}
