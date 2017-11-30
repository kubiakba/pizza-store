package pl.bk.pizza.store

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import pl.bk.pizza.store.application.dto.order.NewOrderDTO
import pl.bk.pizza.store.application.dto.order.OrderDTO
import pl.bk.pizza.store.application.dto.order.discount.DiscountDTO
import pl.bk.pizza.store.application.dto.order.discount.StandardDiscountDTO
import pl.bk.pizza.store.application.dto.product.NewProductDTO
import pl.bk.pizza.store.application.dto.product.PizzaDTO
import pl.bk.pizza.store.application.dto.user.AddressDTO
import pl.bk.pizza.store.application.dto.user.NewUserDTO
import pl.bk.pizza.store.application.dto.user.TelephoneDTO
import pl.bk.pizza.store.application.dto.user.UserDTO
import pl.bk.pizza.store.common.BasicSpecification
import pl.bk.pizza.store.domain.exception.ErrorCode
import pl.bk.pizza.store.domain.order.Status
import pl.bk.pizza.store.domain.product.Dough
import pl.bk.pizza.store.domain.product.PizzaSize

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
class OrderSpecification extends BasicSpecification {

    def "should create order and get it"(){
        given:
            // create user
            def email = "email@e.pl"
            NewUserDTO user = getNewUserDTOStub(email)
            post("/users", user)
        when:
            // create order
            def newOrderDTO = new NewOrderDTO(email: email)
            def response = post("/orders", newOrderDTO)
        then:
            response.getStatusCode() == HttpStatus.CREATED
            URI uri = response.getHeaders().getLocation()
        when:
            // get order
            response = get(uri.toString(), OrderDTO)
        then:
            response.getStatusCode() == HttpStatus.OK
            with(response.body){
                status == Status.STARTED
                userEmail == email
                products.size() == 0
            }
    }

    def "should create order and add product"(){
        given:
            //create user
            def email = "email@e.pl"
            NewUserDTO user = getNewUserDTOStub(email)
            post("/users", user)
        when:
            //create order
            def newOrderDTO = new NewOrderDTO(email: email)
            def response = post("/orders", newOrderDTO)
        then:
            response.getStatusCode() == HttpStatus.CREATED
        and:
            String orderId = response.body.id
        when:
            // create product
            NewProductDTO productDTO = getProductDTOStub()
            response = post("/products", productDTO)
            String productId = response.body.id

            // add product to order
            response = put("/orders/$orderId/$productId", Boolean)
        then:
            response.getStatusCode() == HttpStatus.NO_CONTENT
        when:
            // get order
            response = get("/orders/$orderId", OrderDTO)
        then:
            response.getStatusCode() == HttpStatus.OK
            response.body.products.size() == 1
    }

    def "should start realization"(){
        given:
            // create user
            def email = "email@e.pl"
            NewUserDTO user = getNewUserDTOStub(email)
            post("/users", user)

            //create order
            def newOrderDTO = new NewOrderDTO(email: email)
            def response = post("/orders", newOrderDTO)
            String orderId = response.body.id
        when:
            // start order realization
            put("/orders/$orderId/to-realization", String)
            response = get("/orders/$orderId", OrderDTO)
        then:
            response.body.status == Status.TO_REALIZATION
    }

    def "should calculate total price"(){
        given:
            // create user
            def email = "email@gmail.pl"
            post("/users", getNewUserDTOStub(email))

            //create order
            def newOrderDTO = new NewOrderDTO(email: email)
            def response = post("/orders", newOrderDTO)
            String orderId = response.body.id

            //create product
            NewProductDTO productDTO = getProductDTOStub()
            response = post("/products", productDTO)
            String productId = response.body.id

            //add products to order
            2.times {put("/orders/$orderId/$productId", Boolean)}
        when:
            // get order
            response = get("/orders/$orderId", OrderDTO)
        then:
            response.body.totalPrice == new BigDecimal("6.0")
    }

    def "should validate userEmail"(){
        given:
            def newOrderDTO = new NewOrderDTO(email: email)
        when:
            def response = post("/orders", newOrderDTO)
        then:
            response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY
            with(response.body){
                errorCode == ErrorCode.EMPTY_USER_EMAIL.toString()
            }
        where:
            email << ["", " ", null]
    }

    def "should apply discount to order"(){
        given:
            // create user
            def email = "email@e.pl"
            NewUserDTO user = getNewUserDTOStub(email)
            post("/users", user)

            // create order
            def newOrderDTO = new NewOrderDTO(email: email)
            def response = post("/orders", newOrderDTO)
            String orderId = response.body.id

            //create product
            NewProductDTO productDTO = getProductDTOStub()
            response = post("/products", productDTO)
            String productId = response.body.id

            //add products to order
            2.times{put("/orders/$orderId/$productId", Boolean)}
        when:
            //apply discount to order
            post("/orders/discounts/$orderId", getNewDiscountDTOStub())
            response = get("/orders/$orderId", OrderDTO)
        then:
            response.body.discounts.size() == 1
            response.body.totalPrice == new BigDecimal("3.0")
    }

    def "should start apply points"(){
        given:
            // create user
            def email = "email@e.pl"
            NewUserDTO user = getNewUserDTOStub(email)
            post("/users", user)

            //create order
            def newOrderDTO = new NewOrderDTO(email: email)
            def response = post("/orders", newOrderDTO)
            String orderId = response.body.id

            //create product
            NewProductDTO productDTO = getProductDTOStub()
            response = post("/products", productDTO)
            String productId = response.body.id

            //add products to order
            30.times{put("/orders/$orderId/$productId", Boolean)}
        when:
            // set order to delivered
            put("/orders/$orderId/delivered", String)
            response = get("/users/$email", UserDTO)
        then:
            response.body.points == 9
    }


    private static NewUserDTO getNewUserDTOStub(String email){
        AddressDTO addressDTO = new AddressDTO(city: "Poz", street: "ul", streetNumber: "4", postCode: "49-399")
        TelephoneDTO telephoneDTO = new TelephoneDTO(number: "222")
        return new NewUserDTO(email :email, password: "pass", name: "Adam", surname:"Kot", address: addressDTO ,telephone: telephoneDTO )
    }

    private static NewProductDTO getProductDTOStub(){
        def productInfo = new PizzaDTO(size: PizzaSize.BIG, dough: Dough.THICK)
        return new NewProductDTO(price:new BigDecimal("3.0"), productInfo: productInfo )
    }

    private static DiscountDTO getNewDiscountDTOStub(){
        return new StandardDiscountDTO(discountPercent: new BigDecimal("0.5"))
    }
}
