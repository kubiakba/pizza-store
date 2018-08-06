package pl.bk.pizza.store.helpers.stubs

import pl.bk.pizza.store.application.dto.order.AddressDTO
import pl.bk.pizza.store.application.dto.order.DeliveryInfoDTO
import pl.bk.pizza.store.application.dto.order.NewOrderDTO
import pl.bk.pizza.store.application.dto.order.TelephoneDTO

class OrderStub
{
    static DeliveryInfoDTO getNewDeliveryInfoStub()
    {
        return new DeliveryInfoDTO("Adam", "Stych", getNewAddressDTOStub(), getNewTelephoneDTOStub());
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

    static DeliveryInfoDTO getNewDeliveryInfoDTOStubParam(Map map = [:])
    {
        def name = map.get('name', "Adam")
        def surname = map.get('surname', "Wawrzyniak")
        def city = map.get('city', "Poznan")
        def street = map.get('street', "Poznanska")
        def streetNumber = map.get('streetNumber', "23")
        def postCode = map.get('postCode', "32-222")
        def telephone = map.get('telephone', "332333222")

        new DeliveryInfoDTO(
                name, surname,
                new AddressDTO(city, street, streetNumber, postCode),
                new TelephoneDTO(telephone)
            )
    }
}
