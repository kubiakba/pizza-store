package pl.bk.pizza.store.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bk.pizza.store.application.dto.product.NewProductDTO;
import pl.bk.pizza.store.application.dto.product.NewProductPriceDTO;
import pl.bk.pizza.store.application.dto.product.ProductDTO;
import pl.bk.pizza.store.application.mapper.product.GenericProductMapper;
import pl.bk.pizza.store.application.mapper.product.NewProductMapper;
import pl.bk.pizza.store.domain.exception.MissingEntityException;
import pl.bk.pizza.store.domain.product.Product;
import pl.bk.pizza.store.domain.product.ProductInfo;
import pl.bk.pizza.store.domain.product.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static pl.bk.pizza.store.domain.exception.ErrorCode.MISSING_PRODUCT;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

@Service
@AllArgsConstructor
public class ProductService
{
    private final ProductRepository productRepository;
    private final NewProductMapper newProductMapper;
    private final GenericProductMapper genericProductMapper;
    
    // TODO check warnings
    public Mono<ProductDTO> createProduct(NewProductDTO newProduct)
    {
        final Mono<Product> product = Mono.just(newProductMapper.mapFromDTO(newProduct))
                                          .doOnNext(productRepository::save);
        return product.map(genericProductMapper::mapToDTO);
    }
    
    public Mono<ProductDTO> getProduct(String productId)
    {
        return productRepository
            .findById(productId)
            .doOnNext((product) -> check(product == null, () -> new MissingEntityException(
                "Product with id: " + productId + "does not exists",
                MISSING_PRODUCT
            )))
            .map(genericProductMapper::mapToDTO);
    }
    
    public Flux<ProductDTO> getAllProducts()
    {
        return productRepository
            .findAll()
            .map(genericProductMapper::mapToDTO);
    }
    
    public Flux<ProductDTO> getAllAvailableProducts()
    {
        return productRepository
            .findAll()
            .filter(Product::isAvailable)
            .map(genericProductMapper::mapToDTO);
    }
    
    public void changeProductPrice(String productId, NewProductPriceDTO newProductPriceDTO)
    {
        productRepository
            .findById(productId)
            .doOnNext(product -> check(product == null, () -> new MissingEntityException(
                "Product with id: " + productId + "does not exists",
                MISSING_PRODUCT
            )))
            .doOnNext(product -> product.changePrice(newProductPriceDTO.getPrice()))
            .doOnNext(productRepository::save);
    }
    
    public void makeProductNonAvailable(String productId)
    {
        productRepository
            .findById(productId)
            .doOnNext(product -> check(product == null, () -> new MissingEntityException(
                "Product with id: " + productId + "does not exists",
                MISSING_PRODUCT
            )))
            .doOnNext(Product::makeNonavailable)
            .doOnNext(productRepository::save);
    }
    
    //TODO use example of and test how it works
    public Flux<ProductDTO> getAllProducts(Class<? extends ProductInfo> clazz)
    {
        return productRepository
            .findAll()
            .map(genericProductMapper::mapToDTO);
    }
}
