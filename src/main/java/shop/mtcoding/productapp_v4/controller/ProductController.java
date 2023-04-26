package shop.mtcoding.productapp_v4.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.mtcoding.productapp_v4.dto.product.ProductReqDto.ProductSaveDto;
import shop.mtcoding.productapp_v4.dto.product.ProductReqDto.ProductUpdateDto;
import shop.mtcoding.productapp_v4.handler.exception.CustomException;
import shop.mtcoding.productapp_v4.model.product.Product;
import shop.mtcoding.productapp_v4.model.product.ProductRepository;
import shop.mtcoding.productapp_v4.model.user.User;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HttpSession session;

    // 상품 목록 페이지
    @GetMapping({ "/product", "/" })
    public String productList(Model model) {

        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);
        return "product/productList";
    }

    // 상품 상세 페이지
    @GetMapping("/product/{productId}")
    public String productDetail(@PathVariable Integer productId, Model model) {

        Product product = productRepository.findById(productId);
        model.addAttribute("product", product);

        return "product/productDetail";
    }

    // 상품 등록 페이지
    @GetMapping("/productSave")
    public String productSave() {

        // 관리자 로그인 한 사람만 구매할 수 있음
        User principal = (User) session.getAttribute("principal");
        if (principal == null || !principal.getRole().equals("ADMIN")) {
            throw new CustomException("관리자 로그인을 먼저 해 주세요.", HttpStatus.FORBIDDEN);
        }
        return "product/productSave";

    }

    // 상품 수정 페이지
    @GetMapping("/productUpdate")
    public String productUpdate() {

        // 관리자 로그인 한 사람만 업데이트 할 수 있음
        User principal = (User) session.getAttribute("principal");
        if (principal == null || !principal.getRole().equals("ADMIN")) {
            throw new CustomException("관리자 로그인을 먼저 해 주세요.", HttpStatus.FORBIDDEN);
        }

        return "product/productUpdate";
    }

    // 상품 등록
    @PostMapping("/product/save")
    public String save(ProductSaveDto productSaveDto) {

        // 관리자 로그인 한 사람만 상품 등록할 수 있음
        User principal = (User) session.getAttribute("principal");
        if (principal == null || !principal.getRole().equals("ADMIN")) {
            throw new CustomException("관리자 로그인을 먼저 해 주세요.", HttpStatus.FORBIDDEN);
        }

        // 새로운 상품 등록(insert)
        int result = productRepository.insert(productSaveDto);

        // result 가 1이 아니면 업데이트 안된 것
        if (result != 1) {
            throw new CustomException("상품 등록을 실패했습니다.", HttpStatus.BAD_REQUEST);
        }
        // result == 1 업데이트 성공
        return "redirect:/product";
    }

    // 상품명 중복체크 컨트롤러
    @PostMapping("/productSave/checkName")
    public ResponseEntity<?> checkProductName(@RequestParam String productName) {

        // 관리자 로그인 한 사람만 상품 수정 가능
        User principal = (User) session.getAttribute("principal");
        if (principal == null || !principal.getRole().equals("ADMIN")) {
            throw new CustomException("관리자 로그인을 먼저 해 주세요.", HttpStatus.FORBIDDEN);
        }

        // 디버깅
        System.out.println("productName : " + productName);

        // DB에 중복이 된 값이 있는 지 확인
        Product pn = productRepository.findByName(productName);

        if (pn != null) {
            // pn이 있다면 flase 반환
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        // pn == null 기존에 없던 상품이기 때문에 true 반환
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    // 상품 수정 페이지
    @GetMapping("/product/{productId}/updateForm")
    public String productUpdate(@PathVariable Integer productId, Model model) {

        // 관리자 로그인 한 사람만 상품 수정 가능
        User principal = (User) session.getAttribute("principal");
        if (principal == null || !principal.getRole().equals("ADMIN")) {
            throw new CustomException("관리자 로그인을 먼저 해 주세요.", HttpStatus.FORBIDDEN);
        }

        // Product product = productRepository.findById(id);
        // model.addAttribute("product", product);
        Product product = productRepository.findById(productId);
        model.addAttribute("product", product);

        return "product/productUpdate";
    }

    // 상품 수정
    @PostMapping("/product/{productId}/update")
    public String update(@PathVariable Integer productId, Model model, ProductUpdateDto productUpdateDto) {

        // 관리자 로그인 한 사람만 상품 수정 가능
        User principal = (User) session.getAttribute("principal");
        if (principal == null || !principal.getRole().equals("ADMIN")) {
            throw new CustomException("관리자 로그인을 먼저 해 주세요.", HttpStatus.FORBIDDEN);
        }

        Product p = productRepository.findById(productId);
        model.addAttribute("product", p);

        Product product = new Product();
        product.setProductId(productUpdateDto.getProductId());
        product.setProductName(productUpdateDto.getProductName());
        product.setProductPrice(productUpdateDto.getProductPrice());
        product.setProductQty(productUpdateDto.getProductQty());

        // 업데이트
        int result = productRepository.update(product);

        if (result != 1) {
            System.out.println("업데이트 실패");
            throw new CustomException("업데이트 실패", HttpStatus.BAD_REQUEST);
        }

        // 업데이트 완료
        return "redirect:/product/" + productId;
    }

    // 상품 삭제
    @PostMapping("/product/{productId}/delete")
    public String delete(@PathVariable Integer productId) {

        // 관리자 로그인 한 사람만 상품 삭제 가능
        User principal = (User) session.getAttribute("principal");
        if (principal == null || !principal.getRole().equals("ADMIN")) {
            throw new CustomException("관리자 로그인을 먼저 해 주세요.", HttpStatus.FORBIDDEN);
        }

        int result = productRepository.deleteById(productId);
        if (result != 1) {
            throw new CustomException("삭제 실패", HttpStatus.BAD_REQUEST);
        }
        return "redirect:/product";
    }

}
