package pl.bk.pizza.store.application.mapper.product;

import org.springframework.stereotype.Component;
import pl.bk.common.dto.product.DoughDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.product.pizza.Dough;

@Component
public class DoughMapper implements GenericMapper<DoughDTO,Dough>
{
    @Override
    public Dough mapFromDTO(DoughDTO doughDTO)
    {
        return Dough.valueOf(doughDTO.name());
    }
    
    @Override
    public DoughDTO mapToDTO(Dough dough)
    {
        return DoughDTO.valueOf(dough.name());
    }
}
