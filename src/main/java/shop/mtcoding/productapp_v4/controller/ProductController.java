package shop.mtcoding.productapp_v4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/product")
    public String productList() {
        return "product/productList";
    }

    @GetMapping("/product/{id}")
    public String productDetail() {
        return "product/productDetail";
    }

    @GetMapping("/productSave")
    public String productSave() {
        return "product/productSave";
    }

    @GetMapping("/productUpdate")
    public String productUpdate() {
        return "product/productUpdate";
    }

}
