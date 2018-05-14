package pl.bk.pizza.store

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import pl.bk.pizza.store.application.dto.order.discount.DiscountDTO
import pl.bk.pizza.store.application.dto.order.discount.PercentDiscountDTO
import pl.bk.pizza.store.application.dto.product.NewProductDTO
import pl.bk.pizza.store.application.dto.product.PizzaDTO
import pl.bk.pizza.store.application.dto.user.AddressDTO
import pl.bk.pizza.store.application.dto.user.NewUserDTO
import pl.bk.pizza.store.application.dto.user.TelephoneDTO
import pl.bk.pizza.store.common.BasicSpecification
import pl.bk.pizza.store.domain.order.OrderRepository
import pl.bk.pizza.store.domain.product.pizza.Dough
import pl.bk.pizza.store.domain.product.pizza.PizzaSize
import pl.bk.pizza.store.domain.report.OrderReport

import java.nio.file.Files

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("no-security")
class OrderSpecification extends BasicSpecification {

    @Autowired
    private OrderRepository orderRepository

    @Autowired
    private OrderReport orderReport

    //TODO check if tests are reasonable

    def "should create order and get it"(){

    }

    def "should create order and add product"(){

    }

    def "should start realization"(){

    }

    def "should calculate total price"(){

    }

    def "should validate userEmail"(){

    }

    def "should apply discount to order"(){

    }

    def "should start apply points"(){

    }

    def "should create order and find it via findLastOrders()"(){

    }

    def "should generate order report"(){

    }

    //TODO Check if these methods are ok for future tests

    private void removeNewestFile(File file) {
        File newestFile = file.listFiles().max {it.lastModified()}
        Files.delete(newestFile.toPath())
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
        return new PercentDiscountDTO(discountPercent: new BigDecimal("0.5"))
    }
}
