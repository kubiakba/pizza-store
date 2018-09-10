package pl.bk.pizza.store.application.mapper.customer;

import org.springframework.stereotype.Component;
import pl.bk.common.dto.order.AddressDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.customer.Address;

@Component
public class AddressMapper implements GenericMapper<AddressDTO, Address>
{
    @Override
    public Address mapFromDTO(AddressDTO addressDTO)
    {
        return new Address(
            addressDTO.getCity(),
            addressDTO.getStreet(),
            addressDTO.getStreetNumber(),
            addressDTO.getPostCode()
        );
    }
    
    @Override
    public AddressDTO mapToDTO(Address address)
    {
        return new AddressDTO(
            address.getCity(),
            address.getStreet(),
            address.getStreetNumber(),
            address.getPostCode()
        );
    }
}
