package shop.mtcoding.productapp_v4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {

    @GetMapping("/orders")
    public String ordersList() {
        return "orders/ordersList";
    }
}
