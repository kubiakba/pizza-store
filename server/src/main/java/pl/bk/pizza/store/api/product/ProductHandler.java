package pl.bk.pizza.store.api.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.bk.pizza.store.api.ErrorHandler;
import pl.bk.pizza.store.application.dto.product.input.NewProductDTO;
import pl.bk.pizza.store.application.dto.product.output.ProductDTO;
import pl.bk.pizza.store.application.service.ProductService;
import pl.bk.pizza.store.domain.product.kebab.Kebab;
import pl.bk.pizza.store.domain.product.pizza.Pizza;
import pl.bk.pizza.store.domain.product.pizza.PizzaTopping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.created;

@Component
@AllArgsConstructor
public class ProductHandler
{
    private final ProductService productService;
    
    public Mono<ServerResponse> createProduct(ServerRequest request)
    {
        return request.bodyToMono(NewProductDTO.class)
                      .flatMap(productService::createProduct)
                      .flatMap(it -> created(URI.create("/products/" + it.getId())).body(fromObject(it)))
                      .onErrorResume(ErrorHandler::handleException);
    }
    
    public Mono<ServerResponse> getProduct(ServerRequest request)
    {
        final String productId = request.pathVariable("productId");
        return productService.getProduct(productId)
                             .flatMap(it -> ServerResponse.ok().body(fromObject(it)))
                             .onErrorResume(ErrorHandler::handleException);
    }
    
    public Mono<ServerResponse> getAllProducts(ServerRequest request)
    {
        final Flux<ProductDTO> products = productService.getAllProducts();
        
        return ServerResponse.ok().body(fromObject(products))
                             .onErrorResume(ErrorHandler::handleException);
    }
    
    public Mono<ServerResponse> getAllPizzas(ServerRequest request)
    {
        final Flux<ProductDTO> products = productService.getAllProducts(Pizza.class);
        
        return ServerResponse.ok().body(products, ProductDTO.class)
                             .onErrorResume(ErrorHandler::handleException);
    }
    
    public Mono<ServerResponse> getAllKebabs(ServerRequest request)
    {
        final Flux<ProductDTO> products = productService.getAllProducts(Kebab.class);
        
        return ServerResponse.ok().body(fromObject(products))
                             .onErrorResume(ErrorHandler::handleException);
    }
    
    public Mono<ServerResponse> getAllPizzaToppings(ServerRequest request)
    {
        final Flux<ProductDTO> products = productService.getAllProducts(PizzaTopping.class);
        
        return ServerResponse.ok().body(fromObject(products))
                             .onErrorResume(ErrorHandler::handleException);
    }
}

