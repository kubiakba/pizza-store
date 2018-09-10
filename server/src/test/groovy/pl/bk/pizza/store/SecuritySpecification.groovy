package pl.bk.pizza.store

import org.springframework.beans.factory.annotation.Autowired
import pl.bk.common.dto.user.NewUserDTO
import pl.bk.pizza.store.domain.customer.user.UserFactory
import pl.bk.pizza.store.domain.exception.ErrorCode
import pl.bk.pizza.store.helpers.SecurityCommonSpecification
import pl.bk.pizza.store.infrastructure.security.jwt.JwtAuthenticationRequest

import static org.assertj.core.api.Assertions.assertThat
import static pl.bk.pizza.store.helpers.stubs.ProductStub.newPizzaDTOStub

class SecuritySpecification extends SecurityCommonSpecification
{
    @Autowired
    UserFactory factory

    def 'should generate jwt token'()
    {
        given:
        def user = new NewUserDTO('1234@wp.pl', 'password')
        createUser(user)

        when:
        def token = generateJwtToken(new JwtAuthenticationRequest(user.email, user.password))

        then:
        assertThat(token.token).isNotNull()
        assertThat(token.username).isNotNull()
    }

    def 'should not generate jwt token'()
    {
        given:
        def user = new NewUserDTO('1234@wp.pl', 'password')

        when:
        def error = generateJwtTokenWithError(new JwtAuthenticationRequest(user.email, user.password))

        then:
        assertThat(error.errorCode).isEqualTo(ErrorCode.MISSING_USER)
    }

    def 'should not get secured resource'()
    {
        when:
        def error = createProductWithSecurityError(getNewPizzaDTOStub())

        then:
        error.expectStatus().isUnauthorized()
    }

    def 'should get secured resource'()
    {
        given:
        def user = factory.createWithRole("1234@gmail.com", "password", "ADMIN")
        operations.insert(user, "user").block()

        when:
        def token = generateJwtToken(new JwtAuthenticationRequest("1234@gmail.com", "password"))
        def product = createProductWithAuthorizationToken(getNewPizzaDTOStub(), token.token)

        then:
        assertThat(product).isNotNull()
    }
}
