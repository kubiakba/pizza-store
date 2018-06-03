package pl.bk.pizza.store.validators

import pl.bk.pizza.store.helpers.CommonSpecification

import static pl.bk.pizza.store.domain.exception.ErrorCode.USER_EXISTS
import static pl.bk.pizza.store.helpers.stubs.UserStub.getNewUserDTOStub

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
