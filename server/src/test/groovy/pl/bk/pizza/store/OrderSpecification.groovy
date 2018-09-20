package pl.bk.pizza.store

import pl.bk.common.dto.order.OrderDTO
import pl.bk.common.dto.order.discount.TotalPriceDiscountDTO
import pl.bk.pizza.store.helpers.CommonSpecification

import static java.math.BigDecimal.ONE
import static java.math.BigDecimal.ZERO
import static org.assertj.core.api.Assertions.assertThat
import static pl.bk.common.dto.order.OrderStatusDTO.*
import static pl.bk.pizza.store.helpers.stubs.OrderStub.getNewDeliveryInfoStub
import static pl.bk.pizza.store.helpers.stubs.ProductStub.getNewPizzaDTOStub
import static pl.bk.pizza.store.helpers.stubs.UserStub.getNewUserDTOStub

class OrderSpecification extends CommonSpecification
{
    def "should create order"()
    {
        when:
        def body = createOrder("1@gmail.com", getNewDeliveryInfoStub())

        then:
        with(body) {
            assertThat(id).isNotNull()
            assertThat(userEmail).isEqualTo("1@gmail.com")
            assertThat(products).isEmpty()
            assertThat(orderStatus).isEqualTo(STARTED)
            assertThat(totalPrice).isEqualTo(ZERO)
        }
    }

    def "should get order"()
    {
        given:
        def order = createOrder("1@gmail.com", getNewDeliveryInfoStub())

        when:
        def response = getOrder(order.id)

        then:
        assertThat(response).isNotNull()
    }

    def "should create order and add product to order"()
    {
        when:
        def order = createOrder("1@gmail.com", getNewDeliveryInfoStub())

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
        def order = createOrder("1@gmail.com", getNewDeliveryInfoStub())

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

        def order = createOrder("1@gmail.com", getNewDeliveryInfoStub())

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
        def order = createOrder(email, getNewDeliveryInfoStub())

        and: "add product to order"
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
        def order = createOrder(email, getNewDeliveryInfoStub())
        addProductToOrder(order.id, product.id)

        and: "start order"
        startOrderRealization(order.id)

        when:
        deliverOrder(order.id)
        def user = getUser(email)

        then:
        assertThat(user.points).isEqualTo(3)
    }

    def "should generate report"()
    {
        given: "create user"
        def email = "aa@wp.pl"
        createUser(getNewUserDTOStub(email))

        and: "create product"
        def productDto = getNewPizzaDTOStub()
        def product = createProduct(productDto)

        and: "add product to order"
        def order = createOrder(email, getNewDeliveryInfoStub())
        addProductToOrder(order.id, product.id)

        and: "start and deliver order"
        startOrderRealization(order.id)
        deliverOrder(order.id)

        expect:
        generateReport(1)
    }

    def "should add discount to order"()
    {
        given:
        OrderDTO order = getOrderWithProduct()

        and: "create discount"
        def discount = new TotalPriceDiscountDTO(ZERO, ONE)

        when:
        def orderAfterDiscount = addDiscountToOrder(order.id, discount)

        then:
        assertThat(orderAfterDiscount.discounts.size()).isEqualTo(1)
    }

    def "should apply discount to order"()
    {
        given:
        OrderDTO order = getOrderWithProduct()

        and:
        startOrderRealization(order.id)

        and: "create discount"
        def discount = new TotalPriceDiscountDTO(ZERO, ONE)

        and:
        addDiscountToOrder(order.id, discount)

        when:
        def orderWithAppliedDiscounts = applyDiscounts(order.id)

        then:
        assertThat(orderWithAppliedDiscounts.totalPriceWithDiscount)
            .isEqualTo(order.getProducts().get(0).price.subtract(discount.moneyToReturn))
    }

    private OrderDTO getOrderWithProduct()
    {
        def email = "aa@wp.pl"
        createUser(getNewUserDTOStub(email))

        def product = createProduct(getNewPizzaDTOStub())

        def order = createOrder(email, getNewDeliveryInfoStub())

        return addProductToOrder(order.id, product.id)
    }
}
