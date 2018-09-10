package pl.bk.pizza.store.application.mapper.order;

import org.springframework.stereotype.Component;
import pl.bk.common.dto.order.AddressDTO;
import pl.bk.common.dto.order.DeliveryInfoDTO;
import pl.bk.common.dto.order.TelephoneDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.customer.Address;
import pl.bk.pizza.store.domain.customer.Telephone;
import pl.bk.pizza.store.domain.order.DeliveryInfo;

@Component
public class DeliveryInfoMapper implements GenericMapper<DeliveryInfoDTO, DeliveryInfo>
{
    
    @Override
    public DeliveryInfo mapFromDTO(DeliveryInfoDTO deliveryInfoDTO)
    {
        return new DeliveryInfo(
            deliveryInfoDTO.getName(),
            deliveryInfoDTO.getSurname(),
            new Address(
                deliveryInfoDTO.getAddressDTO().getCity(),
                deliveryInfoDTO.getAddressDTO().getStreet(),
                deliveryInfoDTO.getAddressDTO().getStreetNumber(),
                deliveryInfoDTO.getAddressDTO().getPostCode()
            ),
            new Telephone(deliveryInfoDTO.getTelephoneDTO().getNumber())
        );
    }
    
    @Override
    public DeliveryInfoDTO mapToDTO(DeliveryInfo deliveryInfo)
    {
        return new DeliveryInfoDTO(
            deliveryInfo.getName(),
            deliveryInfo.getSurname(),
            new AddressDTO(
                deliveryInfo.getAddress().getCity(),
                deliveryInfo.getAddress().getStreet(),
                deliveryInfo.getAddress().getStreetNumber(),
                deliveryInfo.getAddress().getPostCode()
            ),
            new TelephoneDTO(deliveryInfo.getTelephone().getNumber())
        );
    }
}
