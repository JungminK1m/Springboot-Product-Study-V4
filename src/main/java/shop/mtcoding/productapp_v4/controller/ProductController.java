package shop.mtcoding.productapp_v4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shop.mtcoding.productapp_v4.model.product.Product;
import shop.mtcoding.productapp_v4.model.product.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public String productList(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);
        return "product/productList";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Integer id, Model model) {

        Product product = productRepository.findById(id);
        model.addAttribute("product", product);

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
