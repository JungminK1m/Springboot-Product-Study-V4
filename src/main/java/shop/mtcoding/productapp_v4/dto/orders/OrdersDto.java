package shop.mtcoding.productapp_v4.dto.orders;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersDto {

    private String ordersName;
    private Integer ordersPrice;
    private Integer ordersQty;
    private Integer productId;

}
