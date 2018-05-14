package pl.bk.pizza.store

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import pl.bk.pizza.store.application.dto.user.AddressDTO
import pl.bk.pizza.store.application.dto.user.NewUserDTO
import pl.bk.pizza.store.application.dto.user.TelephoneDTO
import pl.bk.pizza.store.common.BasicSpecification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("no-security")
class UserSpecification extends BasicSpecification {

    //TODO check if tests are reasonable

    def "should create user and get it"() {

    }

    def "should authorize user"(){

        }

    def "should update user"() {

    }

    def "should return user points"(){

    }

    def "should validate user name"() {

    }

    def "should validate user email"() {

    }

    def "should validate user surname"() {

    }

    def "should validate user address"() {

    }

    def "should validate user telephone"() {

    }

    def "should not create user with email which was used before"() {

    }

    //TODO Check if these methods are ok

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