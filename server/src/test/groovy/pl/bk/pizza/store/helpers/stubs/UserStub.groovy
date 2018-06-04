package pl.bk.pizza.store.helpers.stubs

import pl.bk.pizza.store.application.dto.user.AddressDTO
import pl.bk.pizza.store.application.dto.user.NewUserDTO
import pl.bk.pizza.store.application.dto.user.TelephoneDTO

class UserStub
{
    static NewUserDTO getNewUserDTOStub()
    {
        return new NewUserDTO(
            "aa@wp.pl",
            "pass",
            "Adam",
            "Kot",
            getNewAddressDTOStub(),
            getNewTelephoneDTOStub(),
            "USER"
        )
    }

    static NewUserDTO getNewUserDTOStub(String email)
    {
        return new NewUserDTO(
            email,
            "pass",
            "Adam",
            "Kot",
            getNewAddressDTOStub(),
            getNewTelephoneDTOStub(),
            "USER"
        )
    }

    static TelephoneDTO getNewTelephoneDTOStub()
    {
        return new TelephoneDTO("444-334-343")
    }

    static AddressDTO getNewAddressDTOStub()
    {
        return new AddressDTO(
            "Poznan",
            "poznanska",
            "4",
            "49-399"
        )
    }

    static AddressDTO getNewAddressDTOStubParam(Map map = [:])
    {
        return new AddressDTO(
            map.get('city', "Poznan"),
            map.get('street', "poznanska"),
            map.get('streetNumber', "4"),
            map.get('postCode', "49-399")
        )
    }

    static NewUserDTO getNewUserDTOStubParam(Map map = [:])
    {
        return new NewUserDTO(
            map.get('email', "aa@wp.pl"),
            map.get('password', "pass"),
            map.get('name', "Adam"),
            map.get('surname', "Kot"),
            map.get('address', getNewAddressDTOStub()),
            map.get('telephone', getNewTelephoneDTOStub()),
            map.get('role', "USER")
        )
    }
}
