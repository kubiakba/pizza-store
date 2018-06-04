package pl.bk.pizza.store.validators

import pl.bk.pizza.store.helpers.CommonSpecification
import spock.lang.Unroll

import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_USER_EMAIL
import static pl.bk.pizza.store.domain.exception.ErrorCode.INVALID_EMAIL

class OrderValidator extends CommonSpecification
{
    @Unroll
    def "should throw exception when creating order with invalid #email"()
    {
        when:
        def error = createOrderWithError(email)

        then:
        error.errorCode == errorMessage

        where:
        email  | errorMessage
        "1234" | INVALID_EMAIL
        ""     | EMPTY_USER_EMAIL
        "  "   | EMPTY_USER_EMAIL
    }
}
