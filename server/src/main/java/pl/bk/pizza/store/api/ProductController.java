package pl.bk.pizza.store.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.bk.pizza.store.application.dto.product.in.NewProductDTO;
import pl.bk.pizza.store.application.dto.product.in.NewProductPriceDTO;
import pl.bk.pizza.store.application.dto.product.out.ProductDTO;
import pl.bk.pizza.store.application.service.ProductService;
import pl.bk.pizza.store.domain.product.kebab.Kebab;
import pl.bk.pizza.store.domain.product.pizza.Pizza;
import pl.bk.pizza.store.domain.product.pizza.PizzaTopping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
class ProductController
{
    private final ProductService productService;
    
    @ResponseStatus(CREATED)
    @PostMapping
    public Mono<ProductDTO> createProduct(@RequestBody NewProductDTO newProduct)
    {
        return productService.createProduct(newProduct);
    }
    
    @ResponseStatus(OK)
    @GetMapping("/{productId}")
    public Mono<ProductDTO> getProduct(@PathVariable String productId)
    {
        return productService.getProduct(productId);
    }
    
    @ResponseStatus(OK)
    @GetMapping
    public Flux<ProductDTO> getAllProducts()
    {
        return productService.getAllProducts();
    }
    
    @ResponseStatus(OK)
    @GetMapping("/pizzas")
    public Flux<ProductDTO> getAllPizzas()
    {
        return productService.getAllProducts(Pizza.class);
    }
    
    @ResponseStatus(OK)
    @GetMapping("/kebabs")
    public Flux<ProductDTO> getAllKebabs()
    {
        return productService.getAllProducts(Kebab.class);
    }
    
    @ResponseStatus(OK)
    @GetMapping("/pizzaToppings")
    public Flux<ProductDTO> getAllPizzaToppings()
    {
        return productService.getAllProducts(PizzaTopping.class);
    }
    
    @ResponseStatus(OK)
    @GetMapping("/available")
    public Flux<ProductDTO> getAllAvailableProducts()
    {
        return productService.getAllAvailableProducts();
    }
    
    @ResponseStatus(NO_CONTENT)
    @PutMapping("/{productId}/changePrice")
    public Mono<ProductDTO> changeProductPrice(@RequestBody NewProductPriceDTO newProductPriceDTO, @PathVariable String productId)
    {
        return productService.changeProductPrice(productId, newProductPriceDTO);
    }
    
    @ResponseStatus(NO_CONTENT)
    @PutMapping("/{productId}/non-available")
    public Mono<ProductDTO> makeProductNonAvailable(@PathVariable String productId)
    {
         return productService.makeProductNonAvailable(productId);
    }
}
