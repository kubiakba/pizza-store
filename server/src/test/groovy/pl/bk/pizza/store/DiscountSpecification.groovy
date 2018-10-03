package pl.bk.pizza.store

import pl.bk.common.dto.order.OrderDTO
import pl.bk.common.dto.order.discount.ExtraProductDiscountDTO
import pl.bk.common.dto.order.discount.TotalPriceDiscountDTO
import pl.bk.pizza.store.helpers.CommonSpecification

import static java.math.BigDecimal.ONE
import static java.math.BigDecimal.ZERO
import static org.assertj.core.api.Assertions.assertThat
import static pl.bk.pizza.store.helpers.stubs.OrderStub.getNewDeliveryInfoStub
import static pl.bk.pizza.store.helpers.stubs.ProductStub.getNewPizzaDTOStub
import static pl.bk.pizza.store.helpers.stubs.UserStub.getNewUserDTOStub

class DiscountSpecification extends CommonSpecification
{
    def "should apply TotalPriceDiscount"()
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

    def "should apply ExtraProductDiscount"()
    {
        given:
        OrderDTO order = getOrderWithProduct()

        and:
        startOrderRealization(order.id)

        and: "create discount"
        def discount = new ExtraProductDiscountDTO(0, order.getProducts().get(0).id, 1)

        and:
        addDiscountToOrder(order.id, discount)

        when:
        def orderWithAppliedDiscounts = applyDiscounts(order.id)

        then:
        assertThat(orderWithAppliedDiscounts.products.size())
            .isEqualTo(2)
    }

    def "should apply first TotalPrice and second ExtraProductDiscount"()
    {
        given:
        OrderDTO order = getOrderWithProduct()

        and:
        startOrderRealization(order.id)

        and: "create discounts"
        def discount = new ExtraProductDiscountDTO(0, order.getProducts().get(0).id, 1)
        def discount2 = new TotalPriceDiscountDTO(ZERO, ONE)

        and:
        addDiscountToOrder(order.id, discount)
        addDiscountToOrder(order.id, discount2)

        when:
        def orderWithAppliedDiscounts = applyDiscounts(order.id)

        then:
        assertThat(orderWithAppliedDiscounts.products.size())
            .isEqualTo(2)
        assertThat(orderWithAppliedDiscounts.totalPriceWithDiscount).isEqualByComparingTo(new BigDecimal("29.0"))
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
