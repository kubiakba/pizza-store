package pl.bk.pizza.store

import pl.bk.pizza.store.helpers.CommonSpecification

import static org.assertj.core.api.Assertions.assertThat
import static pl.bk.pizza.store.domain.customer.user.UserStatus.INACTIVE
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
        with(user){
            assertThat(email).isEqualTo(userDto.email)
            assertThat(name).isEqualTo(userDto.name)
            assertThat(surname).isEqualTo(userDto.surname)
            assertThat(telephone.number).isEqualTo(userDto.telephone.number)
            assertThat(points).isEqualTo(0)
        }
        with(user.address){
            assertThat(city).isEqualTo(userDto.address.city)
            assertThat(postCode).isEqualTo(userDto.address.postCode)
            assertThat(street).isEqualTo(userDto.address.street)
            assertThat(streetNumber).isEqualTo(userDto.address.streetNumber)
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

        when:
        def user = createUser(userDto)

        def deactivatedUser = deactivateUser(user.email)

        then:
        assertThat(deactivatedUser.getStatus()).isEqualTo(INACTIVE)
    }
}