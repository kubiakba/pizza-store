package pl.bk.pizza.store

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import pl.bk.pizza.store.application.dto.user.AddressDTO
import pl.bk.pizza.store.application.dto.user.NewUserDTO
import pl.bk.pizza.store.application.dto.user.TelephoneDTO
import pl.bk.pizza.store.application.dto.user.UserDTO
import pl.bk.pizza.store.common.BasicSpecification
import pl.bk.pizza.store.domain.exception.ErrorCode
import pl.bk.pizza.store.domain.user.UserStatus

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static pl.bk.pizza.store.domain.exception.ErrorCode.*

@SpringBootTest(webEnvironment = RANDOM_PORT)
class UserSpecification extends BasicSpecification {

    def "should create user and get it"() {
        when:
            // create user
            def email = "email@e.pl"
            NewUserDTO user = getNewUserDTOStub(email)
            def response = post("/users", user)
        then:
            response.getStatusCode() == HttpStatus.CREATED
        when:
            //get user
            response = get("/users/$email", UserDTO)
        then:
            response.getStatusCode() == HttpStatus.OK
            with(response.body) {
                name == user.name
                surname == user.surname
                address.city == user.address.city
                address.street == user.address.street
                address.streetNumber == user.address.streetNumber
                address.postCode == user.address.postCode
                telephone.number == user.telephone.number
            }
    }

    def "should authorize user"(){
        given:
            // create user
            def email = "email@e.pl"
            def pass = "pass1234"
            NewUserDTO user = getNewUserDTOStub(email, pass)
            post("/users",user)
        when:
            // authorize user
            def response = put("/users/$email/$pass/authenticate", Boolean)
        then:
            response.body == true
        }

    def "should update user"() {
        given:
            //create user
            def email = "email@e.pl"
            NewUserDTO user = getNewUserDTOStub(email)
            post("/users", user)
            //create user to update
            AddressDTO updatedAddressDTO = new AddressDTO(city: "Wro", street: "ulu", streetNumber: "10", postCode: "39-399")
            TelephoneDTO updatedTelephoneDTO = new TelephoneDTO(number: "555")
            NewUserDTO updatedUser = new NewUserDTO(name: "Michal", surname: "Kot", address: updatedAddressDTO, telephone: updatedTelephoneDTO)
        when:
            //update user
            put("/users/$email", updatedUser)
            def response = get("/users/$email", UserDTO)
        then:
            response.getStatusCode() == HttpStatus.OK
            with(response.body) {
                name == updatedUser.name
                surname == updatedUser.surname
                address.city == updatedUser.address.city
                address.street == updatedUser.address.street
                address.streetNumber == updatedUser.address.streetNumber
                address.postCode == updatedUser.address.postCode
                telephone.number == updatedUser.telephone.number
            }
    }

    def "should return user points"(){
        given:
            // create user
            def email = "email@e.pl"
            NewUserDTO user = getNewUserDTOStub(email)
            post("/users",user)
        when:
            // get points
            def response = get("/users/$email/bonus", Integer)
        then:
            response.body.value == 0
    }

    def "should validate user name"() {
        when:
            // create user
            NewUserDTO user = getNewUserDTOStub()
            user.setName(name)
            def response = post("/users", user)
        then:
            response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY
            with(response.body) {
                errorCode == EMPTY_USER_NAME.toString()
            }
        where:
            name << [" ", "", null]
    }

    def "should validate user email"() {
        when:
            // create user
            NewUserDTO user = getNewUserDTOStub()
            user.setEmail(email)
            def response = post("/users", user)
        then:
            response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY
            with(response.body) {
                errorCode == INVALID_EMAIL.toString()
            }
        where:
            email << ["1234", "ssw32"]
    }

    def "should validate user surname"() {
        when:
            // create user
            NewUserDTO user = getNewUserDTOStub()
            user.setSurname(surname)
            def response = post("/users", user)
        then:
            response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY
            with(response.body) {
                errorCode == EMPTY_USER_SURNAME.toString()
            }
        where:
            surname << ["", " ", null]
    }

    def "should validate user address"() {
        when:
            // create user
            NewUserDTO user = getNewUserDTOStub()
            user.setAddress(address)
            def response = post("/users", user)
        then:
            response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY
            with(response.body) {
                errorCode == errorCodeResponse.toString()
            }
        where:
            address                                                                 || errorCodeResponse
            new AddressDTO(city: "", street:"ws",streetNumber:"123", postCode:"33") || EMPTY_CITY
            new AddressDTO(city: "ws", street:"",streetNumber:"123", postCode:"33") || EMPTY_STREET
            new AddressDTO(city: "ws", street:"aws",streetNumber:"", postCode:"33") || EMPTY_STREET_NUMBER
            new AddressDTO(city: "ws", street:"ws",streetNumber:"123", postCode:"") || EMPTY_POSTCODE
    }

    def "should validate user telephone"() {
        when:
            // create user
            NewUserDTO user = getNewUserDTOStub()
            user.setTelephone(address)
            def response = post("/users", user)
        then:
            response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY
            with(response.body) {
                errorCode == EMPTY_NUMBER.toString()
            }
        where:
            address << [new TelephoneDTO(number:""), new TelephoneDTO(number:" ")]

    }

    private static NewUserDTO getNewUserDTOStub(){
        AddressDTO addressDTO = new AddressDTO(city: "Poz", street: "ul", streetNumber: "4", postCode: "49-399")
        TelephoneDTO telephoneDTO = new TelephoneDTO(number: "222")
        NewUserDTO user = new NewUserDTO(email: "1234@gm.com", password: "pass", name: "Adam", surname:"Kot", address: addressDTO,telephone: telephoneDTO)
        return user
    }

    private static NewUserDTO getNewUserDTOStub(String email) {
        AddressDTO addressDTO = new AddressDTO(city: "Poz", street: "ul", streetNumber: "4", postCode: "49-399")
        TelephoneDTO telephoneDTO = new TelephoneDTO(number: "222")
        NewUserDTO user = new NewUserDTO(email: email, password: "pass", name: "Adam", surname: "Kot", address: addressDTO, telephone: telephoneDTO)
        return user
    }

    private static NewUserDTO getNewUserDTOStub(String email, String password) {
        AddressDTO addressDTO = new AddressDTO(city: "Poz", street: "ul", streetNumber: "4", postCode: "49-399")
        TelephoneDTO telephoneDTO = new TelephoneDTO(number: "222")
        NewUserDTO user = new NewUserDTO(email: email, password: password, name: "Adam", surname: "Kot", address: addressDTO, telephone: telephoneDTO)
        return user
    }
}