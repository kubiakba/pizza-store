package pl.bk.pizza.store.application.mapper.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.user.TelephoneDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.customer.telephone.Telephone;
import pl.bk.pizza.store.domain.customer.telephone.TelephoneFactory;

@AllArgsConstructor
@Component
public class TelephoneMapper implements GenericMapper<TelephoneDTO, Telephone>
{
    private final TelephoneFactory factory;
    
    @Override
    public Telephone mapFromDTO(TelephoneDTO telephoneDTO)
    {
        return factory.create(telephoneDTO.getNumber());
    }
    
    @Override
    public TelephoneDTO mapToDTO(Telephone telephone)
    {
        return new TelephoneDTO(telephone.getNumber());
    }
}
