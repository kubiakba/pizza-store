package pl.bk.pizza.store.helpers

import org.springframework.test.web.reactive.server.WebTestClient
import pl.bk.pizza.store.application.dto.user.NewUserDTO
import pl.bk.pizza.store.application.dto.user.UserDTO
import pl.bk.pizza.store.infrastructure.error.ErrorMessage
import pl.bk.pizza.store.infrastructure.security.jwt.JwtAuthenticationRequest
import pl.bk.pizza.store.infrastructure.security.jwt.JwtAuthenticationResponse
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

    JwtAuthenticationResponse generateJwtToken(JwtAuthenticationRequest request)
    {
        client
            .post()
            .uri("/auth")
            .body(Mono.just(request), JwtAuthenticationRequest)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(JwtAuthenticationResponse)
            .returnResult()
            .responseBody
    }

    ErrorMessage generateJwtTokenWithError(JwtAuthenticationRequest request)
    {
        client
            .post()
            .uri("/auth")
            .body(Mono.just(request), JwtAuthenticationRequest)
            .exchange()
            .expectBody(ErrorMessage)
            .returnResult()
            .responseBody
    }

    Integer getUserPoints(String email)
    {
        client
            .method(GET)
            .uri("/users/$email/bonus")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(Integer)
            .returnResult()
            .responseBody
    }

    ErrorMessage createUserWithError(NewUserDTO newUserDTO)
    {
        client
            .post()
            .uri("/users")
            .body(Mono.just(newUserDTO), NewUserDTO)
            .exchange()
            .expectBody(ErrorMessage)
            .returnResult()
            .responseBody
    }
}