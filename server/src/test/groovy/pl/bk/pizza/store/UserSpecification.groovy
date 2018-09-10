package pl.bk.pizza.store

import pl.bk.common.dto.user.UserStatusDTO
import pl.bk.pizza.store.helpers.CommonSpecification

import static org.assertj.core.api.Assertions.assertThat
import static pl.bk.common.dto.user.UserStatusDTO.ACTIVE
import static pl.bk.pizza.store.helpers.stubs.OrderStub.getNewDeliveryInfoStub
import static pl.bk.pizza.store.helpers.stubs.ProductStub.getNewPizzaDTOStub
import static pl.bk.pizza.store.helpers.stubs.UserStub.getNewUserDTOStub

class UserSpecification extends CommonSpecification
{
    def "should create user"()
    {
        given:
        def userDto = getNewUserDTOStub()

        when:
        def user = createUser(userDto)

        then:
        with(user) {
            assertThat(email).isEqualTo(userDto.email)
            assertThat(status).isEqualTo(ACTIVE)
            assertThat(points).isEqualTo(0)
        }
    }

    def "should get user"()
    {
        given:
        def userDto = getNewUserDTOStub()
        createUser(userDto)

        when:
        def user = getUser(userDto.email)

        then:
        assertThat(user).isNotNull()
    }

    def "should deactivate user"()
    {
        given:
        def userDto = getNewUserDTOStub()
        def user = createUser(userDto)

        when:
        def deactivatedUser = deactivateUser(user.email)

        then:
        assertThat(deactivatedUser.getStatus()).isEqualTo(UserStatusDTO.INACTIVE)
    }

    def "should get user points"()
    {
        given:
        def user = createUser(getNewUserDTOStub())
        def product = createProduct(getNewPizzaDTOStub())
        def order = createOrder(user.email, getNewDeliveryInfoStub())

        addProductToOrder(order.id, product.id)
        startOrderRealization(order.id)
        deliverOrder(order.id)

        when:
        def points = getUserPoints(user.email)

        then:
        assertThat(points).isNotEqualTo(0)
    }
}