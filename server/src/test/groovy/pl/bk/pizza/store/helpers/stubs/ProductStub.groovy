package pl.bk.pizza.store.helpers.stubs

import pl.bk.pizza.store.application.dto.product.input.NewKebabDTO
import pl.bk.pizza.store.application.dto.product.input.NewPizzaDTO
import pl.bk.pizza.store.application.dto.product.input.NewPizzaToppingDTO
import pl.bk.pizza.store.application.dto.product.input.NewProductPriceDTO
import pl.bk.pizza.store.domain.product.pizza.Dough
import pl.bk.pizza.store.domain.product.pizza.PizzaSize

import static java.math.BigDecimal.ONE

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

    static NewPizzaToppingDTO getNewPizzaToppingDTOStub()
    {
        return new NewPizzaToppingDTO(ONE, "mushrooms")
    }

    static NewProductPriceDTO getProductPriceStub()
    {
        new NewProductPriceDTO(new BigDecimal("2.5"))
    }
}
