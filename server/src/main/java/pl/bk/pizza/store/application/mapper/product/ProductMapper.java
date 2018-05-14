package pl.bk.pizza.store.application.mapper.product;

import pl.bk.pizza.store.application.dto.product.ProductDTO;
import pl.bk.pizza.store.application.dto.product.ProductInfoDTO;
import pl.bk.pizza.store.application.mapper.DtoToObjectMapper;
import pl.bk.pizza.store.application.mapper.ObjectToDtoMapper;
import pl.bk.pizza.store.domain.product.Product;
import pl.bk.pizza.store.domain.product.ProductInfo;

public interface ProductMapper<T extends ProductInfoDTO, D extends ProductInfo> extends
                                                                                DtoToObjectMapper<ProductDTO<T>, Product<D>>,
                                                                                ObjectToDtoMapper<Product<D>, ProductDTO<T>>
{
}
