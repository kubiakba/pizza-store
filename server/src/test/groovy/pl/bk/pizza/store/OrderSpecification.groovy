package pl.bk.pizza.store

import pl.bk.pizza.store.helpers.CommonSpecification

import static org.assertj.core.api.Assertions.assertThat
import static pl.bk.pizza.store.domain.order.OrderStatus.*
import static pl.bk.pizza.store.helpers.stubs.ProductStub.getNewPizzaDTOStub
import static pl.bk.pizza.store.helpers.stubs.UserStub.getNewUserDTOStub

class OrderSpecification extends CommonSpecification
{
    def "should create order"()
    {
        when:
        def body = createOrder()

        then:
        with(body) {
            assertThat(id).isNotEmpty()
            assertThat(userEmail).isEqualTo(null)
            assertThat(products).isEmpty()
            assertThat(orderStatus).isEqualTo(STARTED)
            assertThat(totalPrice).isEqualTo(BigDecimal.ZERO)
        }
    }

    def "should create order and add product to order"()
    {
        when:
        def order = createOrder()

        def product = createProduct(getNewPizzaDTOStub())

        def orderWithProduct = addProductToOrder(order.id, product.id)

        then:
        assertThat(orderWithProduct.products).hasSize(1)
    }

    def "should start order realization"()
    {
        given:
        def email = "aa@wp.pl"
        createUser(getNewUserDTOStub(email))

        and:
        def order = createOrder(email)

        def product = createProduct(getNewPizzaDTOStub())

        addProductToOrder(order.id, product.id)

        when:
        def orderStarted = startOrderRealization(order.id)

        then:
        assertThat(orderStarted.orderStatus).isEqualTo(TO_REALIZATION)
    }

    def "should calculate total price"()
    {
        given:
        def productDto = getNewPizzaDTOStub()

        def order = createOrder()

        def product = createProduct(productDto)

        addProductToOrder(order.id, product.id)

        when:
        def orderStarted = startOrderRealization(order.id)

        then:
        assertThat(orderStarted.totalPrice).isEqualTo(productDto.price)
    }

    def "should deliver order"()
    {
        given: "create user"
        def email = "aa@wp.pl"
        createUser(getNewUserDTOStub(email))

        and: "create product"
        def product = createProduct(getNewPizzaDTOStub())

        and: "create order"
        def order = createOrder(email)

        and:"add product to order"
        addProductToOrder(order.id, product.id)

        and: "start realization"
        startOrderRealization(order.id)

        when:
        def orderStarted = deliverOrder(order.id)

        then:
        assertThat(orderStarted.orderStatus).isEqualTo(DELIVERED)
    }

    def "should add points to user"()
    {
        given: "create user"
        def email = "aa@wp.pl"
        createUser(getNewUserDTOStub(email))

        and: "create product"
        def productDto = getNewPizzaDTOStub()
        def product = createProduct(productDto)

        and: "add product to order"
        def order = createOrder(email)
        addProductToOrder(order.id, product.id)

        and: "start order"
        startOrderRealization(order.id)

        when:
        deliverOrder(order.id)
        def user = getUser(email)

        then:
        assertThat(user.points).isEqualTo(3)
    }

}
