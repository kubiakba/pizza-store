package pl.bk.pizza.store.application.mapper;

public interface ObjectToDtoMapper<DOMAIN_OBJECT, DTO>
{
    DTO mapToDTO(DOMAIN_OBJECT domainObject);
}
