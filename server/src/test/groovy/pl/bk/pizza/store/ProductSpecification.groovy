package pl.bk.pizza.store

import pl.bk.pizza.store.application.dto.product.output.PizzaDTO
import pl.bk.pizza.store.application.dto.product.output.ProductDTO
import pl.bk.pizza.store.domain.product.ProductStatus
import pl.bk.pizza.store.helpers.CommonSpecification

import static org.assertj.core.api.Assertions.assertThat
import static pl.bk.pizza.store.domain.product.ProductStatus.AVAILABLE
import static pl.bk.pizza.store.helpers.stubs.ProductStub.*

class ProductSpecification extends CommonSpecification
{
    def "should create product"()
    {
        given:
        def productDto = getNewPizzaDTOStub()

        when:
        def product = createProduct(productDto) as PizzaDTO

        then:
        with(product) {
            assertThat(id).isNotEmpty()
            assertThat(price).isEqualTo(productDto.price)
            assertThat(productStatus).isEqualTo(AVAILABLE)
            assertThat(size).isEqualTo(productDto.size)
            assertThat(dough).isEqualTo(productDto.dough)
        }
    }

    def "should make product nonavailable"()
    {
        given:
        def newProductDto = getNewPizzaDTOStub()

        def product = createProduct(newProductDto)

        when:
        def nonAvailableProduct = makeProductNonAvailable(product.id)

        then:
        assertThat(nonAvailableProduct.productStatus).isEqualTo(ProductStatus.NONAVAILABLE)
    }

    def "should change product price"()
    {
        given:
        def newProductDto = getNewPizzaDTOStub()

        def product = createProduct(newProductDto)

        def newPrice = getProductPriceStub()

        when:
        def productWithNewPrice = changeProductPrice(product.id, newPrice)

        then:
        assertThat(productWithNewPrice.price).isEqualTo(newPrice.price)
    }

    def "should get product"()
    {
        given:
        def productDto = createProduct(getNewPizzaDTOStub())

        when:
        def product = getProduct(productDto.id)

        then:
        assertThat(product).isNotNull()
    }

    def "should get pizzas"()
    {
        given:
        createProduct(getNewPizzaDTOStub())
        createProduct(getNewKebabDTOStub())

        when:
        def product = getPizzas()

        then:
        assertThat(product).hasOnlyElementsOfType(ProductDTO)
        assertThat(product.size()).isEqualTo(1)
    }
}
