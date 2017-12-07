package pl.bk.pizza.store.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.bk.pizza.store.application.dto.product.NewProductDTO;
import pl.bk.pizza.store.application.dto.product.NewProductPriceDTO;
import pl.bk.pizza.store.application.dto.product.ProductDTO;
import pl.bk.pizza.store.application.service.ProductService;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody NewProductDTO product){
        ProductDTO productDTO = productService.createProduct(product);
        URI location = URI.create("/products/" + productDTO.getId());
        return ResponseEntity.created(location).body(productDTO);
    }

    @ResponseStatus(OK)
    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @ResponseStatus(OK)
    @GetMapping("/pizzas")
    public List<ProductDTO> getAllPizzas(){ return productService.getAllPizzas(); }

    @ResponseStatus(OK)
    @GetMapping("/kebabs")
    public List<ProductDTO> getAllKebabs(){
        return productService.getAllKebabs();
    }

    @ResponseStatus(OK)
    @GetMapping("/pizzaToppings")
    public List<ProductDTO> getAllPizzaToppings(){
        return productService.getAllPizzaToppings();
    }

    @ResponseStatus(OK)
    @GetMapping("/available")
    public List<ProductDTO> getAllAvailableProducts(){
        return productService.getAllAvailableProducts();
    }

    @ResponseStatus(OK)
    @GetMapping("/{productId}")
    public ProductDTO getProduct(@PathVariable String productId){
        return productService.getProduct(productId);
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping("/{productId}/changePrice")
    public void changeProductPrice(@RequestBody NewProductPriceDTO newProductPriceDTO, @PathVariable String productId){
        productService.changeProductPrice(productId, newProductPriceDTO);
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping("/{productId}/non-available")
    public void makeProductNonAvailable(@PathVariable String productId){
        productService.makeProductNonAvailable(productId);
    }
}
