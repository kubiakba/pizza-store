package pl.bk.pizza.store

import static org.assertj.core.api.Assertions.assertThat
import static pl.bk.pizza.store.domain.order.OrderStatus.*

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
            assertThat(discounts).isEmpty()
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
        when:
        def order = createOrder()

        def product = createProduct(getNewPizzaDTOStub())

        addProductToOrder(order.id, product.id)

        def orderStarted = startOrderRealization(order.id)

        then:
        assertThat(orderStarted.orderStatus).isEqualTo(TO_REALIZATION)
    }

    def "should calculate total price"()
    {
        given:
        def productDto = getNewPizzaDTOStub()

        when:
        def order = createOrder()

        def product = createProduct(productDto)

        addProductToOrder(order.id, product.id)

        def orderStarted = startOrderRealization(order.id)

        then:
        assertThat(orderStarted.totalPrice).isEqualTo(productDto.price)
    }

    def "should deliver order"()
    {
        given:
        def productDto = getNewPizzaDTOStub()

        when:
        def order = createOrder()

        def product = createProduct(productDto)

        addProductToOrder(order.id, product.id)

        def orderStarted = deliverOrder(order.id)
        then:
        assertThat(orderStarted.orderStatus).isEqualTo(DELIVERED)
    }
}
