package pl.bk.pizza.store

import org.assertj.core.api.Assertions
import pl.bk.pizza.store.application.dto.user.NewUserDTO
import pl.bk.pizza.store.domain.exception.ErrorCode
import pl.bk.pizza.store.helpers.CommonSpecification
import pl.bk.pizza.store.infrastructure.security.jwt.JwtAuthenticationRequest

import static org.assertj.core.api.Assertions.assertThat

class SecuritySpecification extends CommonSpecification
{
    def "should generate jwt token"()
    {
        given:
        def user = new NewUserDTO("1234@wp.pl", "password")
        createUser(user)

        when:
        def token = generateJwtToken(new JwtAuthenticationRequest(user.email, user.password))

        then:
        assertThat(token.token).isNotNull()
        assertThat(token.username).isNotNull()
    }

    def "should not generate jwt token"()
    {
        given:
        def user = new NewUserDTO("1234@wp.pl", "password")

        when:
        def error = generateJwtTokenWithError(new JwtAuthenticationRequest(user.email, user.password))

        then:
        assertThat(error.errorCode).isEqualTo(ErrorCode.MISSING_USER)
    }

}
