package pl.bk.pizza.store.application.mapper;

public interface DtoToObjectMapper<DTO, DOMAIN_OBJECT>
{
    DOMAIN_OBJECT mapFromDTO(DTO dto);
}
