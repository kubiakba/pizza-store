package pl.bk.pizza.store

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import pl.bk.pizza.store.application.dto.product.KebabDTO
import pl.bk.pizza.store.application.dto.product.NewProductDTO
import pl.bk.pizza.store.application.dto.product.NewProductPriceDTO
import pl.bk.pizza.store.application.dto.product.PizzaDTO
import pl.bk.pizza.store.application.dto.product.PizzaToppingDTO
import pl.bk.pizza.store.application.dto.product.ProductDTO
import pl.bk.pizza.store.common.BasicSpecification
import pl.bk.pizza.store.domain.exception.ErrorCode
import pl.bk.pizza.store.domain.product.Dough
import pl.bk.pizza.store.domain.product.PizzaSize
import pl.bk.pizza.store.domain.product.Status

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProductSpecification extends BasicSpecification{

    def "should create product and get it"(){
        when:
            // create product
            def productDTO = new NewProductDTO(price:new BigDecimal("1.50"), productInfo: productInfo )
            def response = post("/products", productDTO)
        then:
            response.statusCode == HttpStatus.CREATED
        and:
            URI uri = response.getHeaders().getLocation()
        when:
            // get product
            response = get(uri.toString(), ProductDTO)
        then:
            response.getStatusCode() == HttpStatus.OK
            with(response.body){
            // pizzaDTO
            if(productInfo.class == PizzaDTO){
               price == new BigDecimal("1.50")
               productInfo.size == PizzaSize.BIG
               productInfo.dough == Dough.THICK
            }
            // KebabDTO
            else if(productInfo.class == KebabDTO){
               price == new BigDecimal("1.50")
               productInfo.name == "Doner"
               productInfo.description == "mieso, sos"
            }
            // PizzaToppingsDTO
            else if(productInfo.class == PizzaToppingDTO){
                price == new BigDecimal("1.50")
                productInfo.name == "sos czosnkowy"
            }}
        where:
            productInfo << [new PizzaDTO(size: PizzaSize.BIG, dough: Dough.THICK),
                            new KebabDTO(name: "Doner", description: "mieso, sos"),
                            new PizzaToppingDTO(name: "sos czosnkowy")]

    }

    def "should get all products"(){
        given:
            // create products
            def pizzaDTO = new PizzaDTO(size: PizzaSize.BIG, dough: Dough.THICK)
            def productDTO = new NewProductDTO(price:new BigDecimal("1.50"), productInfo: pizzaDTO )
            2.times {post("/products", productDTO)}
        when:
            // get products
            def response = get("/products", new ParameterizedTypeReference<List<ProductDTO>>(){})
        then:
            response.statusCode == HttpStatus.OK
            response.body.size() == 2
    }

    def "should make product nonavailable"(){
        given:
            // create products
            def pizzaDTO = new PizzaDTO(size: PizzaSize.BIG, dough: Dough.THICK)
            def productDTO = new NewProductDTO(price:new BigDecimal("1.50"), productInfo: pizzaDTO )
            def response = post("/products", productDTO)
            String productId = response.body.id
        when:
            put("/products/$productId/non-available", String)
            // get products
            response = get("/products/$productId", ProductDTO)
        then:
            response.body.status == Status.NONAVAILABLE
            response.statusCode == HttpStatus.OK
    }

    def "should change product price"(){
        given:
            // create product
            def pizzaDTO = new PizzaDTO(size: PizzaSize.BIG, dough: Dough.THICK)
            def productDTO = new NewProductDTO(price:new BigDecimal("1.50"), productInfo: pizzaDTO )
            def response = post("/products", productDTO)
            String productId = response.body.id
            // create priceDTO
            def newPriceDTO = new NewProductPriceDTO(price : new BigDecimal("2.0"))
        when:
            put("/products/$productId/changePrice", newPriceDTO)
            response = get("/products/$productId", ProductDTO)
        then:
            response.body.price == new BigDecimal("2.0")
    }

    def "should validate kebab name"(){
        given:
            def productInfo = new KebabDTO(name: name, description: "mieso, sos")
            def productDTO = new NewProductDTO(price:new BigDecimal("1.50"), productInfo: productInfo )
        when:
            def response = post("/products", productDTO)
        then:
            response.statusCode == HttpStatus.UNPROCESSABLE_ENTITY
            with(response.body){
                errorCode == ErrorCode.EMPTY_KEBAB_NAME.toString()
            }
        where:
            name << ["", " ", null]
    }

    def "should validate kebab description"(){
        given:
            def productInfo = new KebabDTO(name: "kebab maly", description: description)
            def productDTO = new NewProductDTO(price: new BigDecimal("1.50"), productInfo: productInfo )
        when:
            def response = post("/products", productDTO)
        then:
            response.statusCode == HttpStatus.UNPROCESSABLE_ENTITY
            with(response.body){
                errorCode == ErrorCode.EMPTY_KEBAB_DESCRIPTION.toString()
            }
        where:
            description << ["", " ", null]
    }

    def "should validate PizzaTopping name"(){
        given:
            def productInfo = new PizzaToppingDTO(name: name)
            def productDTO = new NewProductDTO(price: new BigDecimal("1.50"), productInfo: productInfo )
        when:
            def response = post("/products", productDTO)
        then:
            response.statusCode == HttpStatus.UNPROCESSABLE_ENTITY
            with(response.body){
                errorCode == ErrorCode.EMPTY_PIZZA_TOPPING_NAME.toString()
            }
        where:
            name << ["", " ", null]
    }
}
