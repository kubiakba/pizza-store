package pl.bk.pizza.store.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pl.bk.common.dto.product.input.NewProductDTO;
import pl.bk.common.dto.product.input.NewProductPriceDTO;
import pl.bk.common.dto.product.output.ProductDTO;
import pl.bk.pizza.store.application.mapper.product.NewProductMapper;
import pl.bk.pizza.store.application.mapper.product.ProductMapper;
import pl.bk.pizza.store.domain.product.BaseProductInfo;
import pl.bk.pizza.store.domain.product.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static pl.bk.pizza.store.domain.validator.product.ProductValidator.productShouldExists;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService
{
    private final ProductRepository productRepository;
    private final NewProductMapper newProductMapper;
    private final ProductMapper productMapper;
    private final ReactiveMongoTemplate mongoTemplate;
    
    public Mono<ProductDTO> createProduct(NewProductDTO newProduct)
    {
        return Mono.just(newProductMapper.mapFromDTO(newProduct))
                   .flatMap(productRepository::save)
                   .doOnNext(product -> log.info("Product with id:[{}] has been added.", product.getId()))
                   .map(productMapper::mapToDTO);
    }
    
    public Mono<ProductDTO> getProduct(String productId)
    {
        return productRepository
            .findById(productId)
            .switchIfEmpty(productShouldExists(productId))
            .map(productMapper::mapToDTO);
    }
    
    public Flux<ProductDTO> getAllProducts()
    {
        return productRepository
            .findAll()
            .map(productMapper::mapToDTO);
    }
    
    public Flux<ProductDTO> getAllAvailableProducts()
    {
        return productRepository
            .findAll()
            .filter(BaseProductInfo::isAvailable)
            .map(productMapper::mapToDTO);
    }
    
    public Mono<ProductDTO> changeProductPrice(String productId, NewProductPriceDTO newProductPriceDTO)
    {
        return productRepository
            .findById(productId)
            .switchIfEmpty(productShouldExists(productId))
            .map(product -> product.changePrice(newProductPriceDTO.getPrice()))
            .flatMap(productRepository::save)
            .map(productMapper::mapToDTO);
    }
    
    public Mono<ProductDTO> makeProductNonAvailable(String productId)
    {
        return productRepository
            .findById(productId)
            .switchIfEmpty(productShouldExists(productId))
            .map(BaseProductInfo::makeNonavailable)
            .flatMap(productRepository::save)
            .map(productMapper::mapToDTO);
    }
    
    public Flux<ProductDTO> getAllProducts(Class<? extends BaseProductInfo> clazz)
    {
        final Query query = new Query().restrict(clazz);
        
        return mongoTemplate
            .find(query, clazz, "product")
            .map(productMapper::mapToDTO);
    }
}
