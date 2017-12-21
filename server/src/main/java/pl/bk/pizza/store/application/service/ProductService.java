package pl.bk.pizza.store.application.service;

import org.springframework.stereotype.Service;

import pl.bk.pizza.store.application.dto.product.NewProductDTO;
import pl.bk.pizza.store.application.dto.product.NewProductPriceDTO;
import pl.bk.pizza.store.application.dto.product.ProductDTO;
import pl.bk.pizza.store.application.utils.DtoMapper;
import pl.bk.pizza.store.domain.exception.MissingEntityException;
import pl.bk.pizza.store.domain.product.Product;
import pl.bk.pizza.store.domain.product.ProductRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static pl.bk.pizza.store.domain.exception.ErrorCode.*;
import static pl.bk.pizza.store.domain.exception.Preconditions.*;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final DtoMapper dtoMapper;

    public ProductService(ProductRepository productRepository, DtoMapper dtoMapper) {
        this.productRepository = productRepository;
        this.dtoMapper = dtoMapper;
    }

    public ProductDTO createProduct(NewProductDTO newProduct) {
        Product product = dtoMapper.mapTo(newProduct);
        productRepository.save(product);
        return dtoMapper.mapTo(product);
    }

    public ProductDTO getProduct(String productId){
        final Product product = productRepository.getProductById(productId);
        check(product == null, () -> new MissingEntityException("Product with id: " + productId + "does not exists",
            MISSING_PRODUCT));
        return dtoMapper.mapTo(product);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.getAllProducts().stream()
            .map(dtoMapper::mapTo)
            .collect(toList());
    }

    public List<ProductDTO> getAllAvailableProducts() {
        return productRepository.getAllProducts().stream()
            .filter(Product::isAvailable)
            .map(dtoMapper::mapTo)
            .collect(toList());
    }

    public void changeProductPrice(String productId, NewProductPriceDTO newProductPriceDTO) {
        final Product product = productRepository.getProductById(productId);
        check(product == null, () -> new MissingEntityException("Product with id: " + productId + "does not exists",
            MISSING_PRODUCT));
        product.changePrice(newProductPriceDTO.getPrice());
        productRepository.save(product);
    }

    public void makeProductNonAvailable(String productId) {
        final Product product = productRepository.getProductById(productId);
        check(product == null, () -> new MissingEntityException("Product with id: " + productId + "does not exists",
            MISSING_PRODUCT));
        product.makeNonavailable();
        productRepository.save(product);
    }

    public List<ProductDTO> getAllPizzas() {
        return productRepository.getAllPizzas()
            .stream()
            .map(dtoMapper::mapTo)
            .collect(toList());
    }

    public List<ProductDTO> getAllKebabs() {
        return productRepository.getAllKebabs()
            .stream()
            .map(dtoMapper::mapTo)
            .collect(toList());
    }

    public List<ProductDTO> getAllPizzaToppings() {
        return productRepository.getAllPizzaToppings()
            .stream()
            .map(dtoMapper::mapTo)
            .collect(toList());
    }

}
