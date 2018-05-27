package pl.bk.pizza.store.helpers.stubs

import pl.bk.pizza.store.application.dto.user.AddressDTO
import pl.bk.pizza.store.application.dto.user.NewUserDTO
import pl.bk.pizza.store.application.dto.user.TelephoneDTO

class UserStub
{
    static NewUserDTO getNewUserDTOStub()
    {
        AddressDTO addressDTO = new AddressDTO("Poznan", "poznanska", "4", "49-399")
        TelephoneDTO telephoneDTO = new TelephoneDTO("444-334-343")
        return new NewUserDTO("aa@wp.pl", "pass", "Adam", "Kot", addressDTO, telephoneDTO, "USER")
    }

    static NewUserDTO getNewUserDTOStub(String email)
    {
        AddressDTO addressDTO = new AddressDTO("Poznan", "poznanska", "4", "49-399")
        TelephoneDTO telephoneDTO = new TelephoneDTO("444-334-343")
        return new NewUserDTO(email, "pass", "Adam", "Kot", addressDTO, telephoneDTO, "USER")
    }
}
