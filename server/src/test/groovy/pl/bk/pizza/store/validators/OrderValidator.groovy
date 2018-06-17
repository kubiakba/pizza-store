package pl.bk.pizza.store.validators

import pl.bk.pizza.store.helpers.CommonSpecification
import spock.lang.Unroll

import static pl.bk.pizza.store.domain.exception.ErrorCode.*
import static pl.bk.pizza.store.helpers.stubs.ProductStub.getNewPizzaDTOStub
import static pl.bk.pizza.store.helpers.stubs.UserStub.getNewUserDTOStub

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

    def "should throw exception when setting order status to deliver order that doesn't start realization"()
    {
        given: "create user"
        def email = "aa@wp.pl"
        createUser(getNewUserDTOStub(email))

        and: "create product"
        def product = createProduct(getNewPizzaDTOStub())

        and: "create order"
        def order = createOrder(email)

        and: "add product to order"
        addProductToOrder(order.id, product.id)

        when:
        def errorMessage = deliverOrderWithError(order.id)

        then:
        errorMessage.getErrorCode() == INVALID_ORDER_STATUS
    }
}
