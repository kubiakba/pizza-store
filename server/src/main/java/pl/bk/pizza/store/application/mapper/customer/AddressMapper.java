package pl.bk.pizza.store.application.mapper.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.user.AddressDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.customer.address.Address;
import pl.bk.pizza.store.domain.customer.address.AddressFactory;

@Component
@AllArgsConstructor
public class AddressMapper implements GenericMapper<AddressDTO, Address>
{
    private final AddressFactory addressFactory;
    
    @Override
    public Address mapFromDTO(AddressDTO addressDTO)
    {
        return addressFactory.create(
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
