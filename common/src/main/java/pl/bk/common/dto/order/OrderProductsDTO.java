package pl.bk.common.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderProductsDTO
{
    private String orderId;
    private String[] productsIds;
}
