package pl.bk.pizza.store.validators

import pl.bk.pizza.store.application.dto.user.TelephoneDTO
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

    @Unroll
    def "should throw exception when creating user with #errorMessage"()
    {
        when:
        def error = createUserWithError(user)

        then:
        error.errorCode == errorMessage

        where:
        user                                                                         | errorMessage
        getNewUserDTOStubParam(email: '')                                            | EMPTY_USER_EMAIL
        getNewUserDTOStubParam(name: '')                                             | EMPTY_USER_NAME
        getNewUserDTOStubParam(surname: '')                                          | EMPTY_USER_SURNAME
        getNewUserDTOStubParam(telephone: new TelephoneDTO(''))              | EMPTY_NUMBER
        getNewUserDTOStubParam(address: getNewAddressDTOStubParam(city: ''))         | EMPTY_CITY
        getNewUserDTOStubParam(address: getNewAddressDTOStubParam(street: ''))       | EMPTY_STREET
        getNewUserDTOStubParam(address: getNewAddressDTOStubParam(streetNumber: '')) | EMPTY_STREET_NUMBER
        getNewUserDTOStubParam(address: getNewAddressDTOStubParam(postCode: ''))     | EMPTY_POSTCODE
    }
}
