package pl.bk.pizza.store

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import pl.bk.pizza.store.application.dto.order.NewOrderDTO
import pl.bk.pizza.store.application.dto.order.OrderDTO
import pl.bk.pizza.store.application.dto.product.in.NewPizzaDTO
import pl.bk.pizza.store.application.dto.product.in.NewProductDTO
import pl.bk.pizza.store.application.dto.product.in.NewProductPriceDTO
import pl.bk.pizza.store.application.dto.product.out.ProductDTO
import pl.bk.pizza.store.application.dto.user.AddressDTO
import pl.bk.pizza.store.application.dto.user.NewUserDTO
import pl.bk.pizza.store.application.dto.user.TelephoneDTO
import pl.bk.pizza.store.application.dto.user.UserDTO
import pl.bk.pizza.store.domain.product.pizza.Dough
import pl.bk.pizza.store.domain.product.pizza.PizzaSize
import reactor.core.publisher.Mono
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@AutoConfigureWebTestClient(timeout = "300000")
@SpringBootTest(webEnvironment = RANDOM_PORT)
abstract class CommonSpecification extends Specification
{
    @Autowired
    protected WebTestClient client

    protected OrderDTO createOrder(String email)
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

    protected ProductDTO createProduct(NewProductDTO product)
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

    protected OrderDTO addProductToOrder(String orderId, String productId)
    {
        client
            .put()
            .uri("/orders/$orderId/$productId")
            .exchange()
            .expectBody(OrderDTO)
            .returnResult()
            .responseBody
    }

    protected OrderDTO startOrderRealization(String id)
    {
        client
            .put()
            .uri("/orders/$id/to-realization")
            .exchange()
            .expectStatus()
            .isNoContent()
            .expectBody(OrderDTO)
            .returnResult()
            .responseBody
    }

    protected OrderDTO deliverOrder(String id)
    {
        client
            .put()
            .uri("/orders/$id/delivered")
            .exchange()
            .expectStatus()
            .isNoContent()
            .expectBody(OrderDTO)
            .returnResult()
            .responseBody
    }

    protected ProductDTO makeProductNonAvailable(String id)
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

    protected changeProductPrice(String id, NewProductPriceDTO productPriceDTO)
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

    protected UserDTO createUser(NewUserDTO newUserDTO)
    {
        client
            .post()
            .uri("/users")
            .body(Mono.just(newUserDTO), NewUserDTO)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(UserDTO)
            .returnResult()
            .responseBody
    }

    protected UserDTO deactivateUser(String email)
    {
        client
            .patch()
            .uri("/users/$email/deactivate")
            .exchange()
            .expectStatus()
            .isNoContent()
            .expectBody(UserDTO)
            .returnResult()
            .responseBody
    }

    protected NewPizzaDTO getNewPizzaDTOStub()
    {
        return new NewPizzaDTO(new BigDecimal("3.0"), PizzaSize.BIG, Dough.THICK)
    }

    protected NewUserDTO getNewUserDTOStub()
    {
        AddressDTO addressDTO = new AddressDTO("Poznan", "poznanska", "4", "49-399")
        TelephoneDTO telephoneDTO = new TelephoneDTO("444-334-343")
        return new NewUserDTO("aa@wp.pl", "pass", "Adam", "Kot", addressDTO, telephoneDTO, "USER")
    }

    protected NewProductPriceDTO getProductPriceStub()
    {
        new NewProductPriceDTO(new BigDecimal("2.5"))
    }
}
