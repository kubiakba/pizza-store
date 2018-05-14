package pl.bk.pizza.store.application.mapper;

public interface GenericMapper<DTO, DOMAIN_OBJECT> extends DtoToObjectMapper<DTO, DOMAIN_OBJECT>, ObjectToDtoMapper<DOMAIN_OBJECT, DTO>
{
}
