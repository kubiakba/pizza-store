package pl.bk.pizza.store.validators

import pl.bk.pizza.store.application.dto.order.TelephoneDTO
import pl.bk.pizza.store.helpers.CommonSpecification
import spock.lang.Unroll

import static pl.bk.pizza.store.domain.exception.ErrorCode.*
import static pl.bk.pizza.store.helpers.stubs.UserStub.*

class UserValidator extends CommonSpecification
{
    def "should throw exception when creating second user with same email"()
    {
        given:
        def userDto = getNewUserDTOStub()

        when:
        createUser(userDto)
        def error = createUserWithError(userDto)

        then:
        error.errorCode == USER_EXISTS
    }
}
