package pl.bk.pizza.store

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import pl.bk.pizza.store.common.BasicSpecification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("no-security")
class ProductSpecification extends BasicSpecification{

    //TODO check if tests are reasonable

    def "should create product and get it"(){

    }

    def "should get all products"(){

    }

    def "should make product nonavailable"(){

    }

    def "should change product price"(){

    }

    def "should validate kebab name"(){

    }

    def "should validate kebab description"(){

    }

    def "should validate PizzaTopping name"(){

    }

    def "should get only pizzas"(){

    }

    def "should get only kebabs"(){

    }

    def "should get only pizza toppings"(){

    }
}
