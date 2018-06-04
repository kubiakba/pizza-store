package pl.bk.pizza.store.api.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.bk.pizza.store.application.dto.product.input.NewProductDTO;
import pl.bk.pizza.store.application.dto.product.input.NewProductPriceDTO;
import pl.bk.pizza.store.application.dto.product.output.ProductDTO;
import pl.bk.pizza.store.application.service.ProductService;
import pl.bk.pizza.store.domain.product.kebab.Kebab;
import pl.bk.pizza.store.domain.product.pizza.Pizza;
import pl.bk.pizza.store.domain.product.pizza.PizzaTopping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
class ProductController
{
    private final ProductService productService;
    
    @ResponseStatus(OK)
    @GetMapping("/available")
    public Flux<ProductDTO> getAllAvailableProducts()
    {
        return productService.getAllAvailableProducts();
    }
    
    @ResponseStatus(OK)
    @PutMapping("/{productId}/changePrice")
    public Mono<ProductDTO> changeProductPrice(@RequestBody NewProductPriceDTO newProductPriceDTO, @PathVariable String productId)
    {
        return productService.changeProductPrice(productId, newProductPriceDTO);
    }
    
    @ResponseStatus(OK)
    @PutMapping("/{productId}/non-available")
    public Mono<ProductDTO> makeProductNonAvailable(@PathVariable String productId)
    {
        return productService.makeProductNonAvailable(productId);
    }
}
