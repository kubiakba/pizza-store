package pl.bk.pizza.store.helpers.stubs

import pl.bk.pizza.store.application.dto.product.input.NewKebabDTO
import pl.bk.pizza.store.application.dto.product.input.NewPizzaDTO
import pl.bk.pizza.store.application.dto.product.input.NewProductPriceDTO
import pl.bk.pizza.store.domain.product.pizza.Dough
import pl.bk.pizza.store.domain.product.pizza.PizzaSize

class ProductStub
{
    static NewPizzaDTO getNewPizzaDTOStub()
    {
        return new NewPizzaDTO(new BigDecimal("30.0"), PizzaSize.BIG, Dough.THICK)
    }

    static NewKebabDTO getNewKebabDTOStub()
    {
        return new NewKebabDTO(new BigDecimal("30.0"), "desc", "name")
    }

    static NewProductPriceDTO getProductPriceStub()
    {
        new NewProductPriceDTO(new BigDecimal("2.5"))
    }
}
