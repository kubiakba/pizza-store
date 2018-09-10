package pl.bk.pizza.store.application.mapper.customer;

import org.springframework.stereotype.Component;
import pl.bk.common.dto.order.TelephoneDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.customer.Telephone;

@Component
public class TelephoneMapper implements GenericMapper<TelephoneDTO, Telephone>
{
    @Override
    public Telephone mapFromDTO(TelephoneDTO telephoneDTO)
    {
        return new Telephone(telephoneDTO.getNumber());
    }
    
    @Override
    public TelephoneDTO mapToDTO(Telephone telephone)
    {
        return new TelephoneDTO(telephone.getNumber());
    }
}
