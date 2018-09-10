package pl.bk.pizza.store.helpers.stubs

import pl.bk.common.dto.product.DoughDTO
import pl.bk.common.dto.product.PizzaSizeDTO
import pl.bk.common.dto.product.input.NewKebabDTO
import pl.bk.common.dto.product.input.NewPizzaDTO
import pl.bk.common.dto.product.input.NewPizzaToppingDTO
import pl.bk.common.dto.product.input.NewProductPriceDTO

import static java.math.BigDecimal.ONE

class ProductStub
{
    static NewPizzaDTO getNewPizzaDTOStub()
    {
        return new NewPizzaDTO(new BigDecimal("30.0"), PizzaSizeDTO.BIG, DoughDTO.THICK)
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
