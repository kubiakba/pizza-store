package pl.bk.pizza.store.helpers

import org.springframework.test.web.reactive.server.WebTestClient
import pl.bk.pizza.store.application.dto.user.NewUserDTO
import pl.bk.pizza.store.application.dto.user.UserDTO
import reactor.core.publisher.Mono

import static org.springframework.http.HttpMethod.GET

trait UserHelper
{
    abstract WebTestClient getClient()

    UserDTO createUser(NewUserDTO newUserDTO)
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

    UserDTO deactivateUser(String email)
    {
        client
            .patch()
            .uri("/users/$email/deactivate")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(UserDTO)
            .returnResult()
            .responseBody
    }

    UserDTO getUser(String email)
    {
        client
            .method(GET)
            .uri("/users/$email")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(UserDTO)
            .returnResult()
            .responseBody
    }

}
