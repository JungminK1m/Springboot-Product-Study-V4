package shop.mtcoding.productapp_v4.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.productapp_v4.dto.orders.OrdersDto;
import shop.mtcoding.productapp_v4.handler.exception.CustomException;
import shop.mtcoding.productapp_v4.model.orders.Orders;
import shop.mtcoding.productapp_v4.model.orders.OrdersRepository;
import shop.mtcoding.productapp_v4.model.product.Product;
import shop.mtcoding.productapp_v4.model.product.ProductRepository;
import shop.mtcoding.productapp_v4.model.user.User;

@Controller
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HttpSession session;

    // 구매 목록 페이지
    @GetMapping("/ordersList/{userId}")
    public String orderListForm(@PathVariable Integer userId, Model model) {

        User principal = (User) session.getAttribute("principal");

        // 로그인 안한 사람이 주문목록 보려고 시도할 시
        if (principal == null) {
            throw new CustomException("구매목록을 볼 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        // 로그인 했지만 나 아닌 다른 사람의 주문목록 보려고 시도할 시
        // ! <- 논리 부정 연산자
        if (!principal.getUserId().equals(userId)) {
            throw new CustomException("구매목록을 볼 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        List<Orders> ordersList = ordersRepository.findAll(userId);
        model.addAttribute("orderedProduct", ordersList);

        return "orders/ordersList";
    }

    // 상품 구매하기
    // 어떤 상품을 구매했는 지 알아야해서 주소에 productId가 필요함(?) <--확인하기
    @PostMapping("/orders/{productId}")
    public String order(@PathVariable Integer productId, OrdersDto ordersDto) {

        // 로그인 한 사람만 구매할 수 있음
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("로그인을 먼저 해 주세요.", HttpStatus.FORBIDDEN);
        }

        // 상품수량보다 구매수량이 더 많으면 안됨
        Product productPS = productRepository.findById(productId);
        if (productPS.getProductQty() - ordersDto.getOrdersQty() < 0) {
            throw new CustomException("재고보다 더 많은 수량을 구매할 수 없습니다.", HttpStatus.FORBIDDEN);
        }

        // 구매를 하면 product qty가 차감되어야 함
        productRepository.productQtyUpdate(ordersDto);

        // principal.getUserId() 너무 길어서 변수로 만듦
        int userId = principal.getUserId();

        /*
         * 구매버튼 누르면 insert 됨
         * 누가 구매했는 지 필요하기 때문에 userId도 같이 insert 해야 함
         */
        ordersRepository.insert(ordersDto, userId);
        return "redirect:/ordersList/" + userId;

    }

    @PostMapping("/ordersList/delete")
    public String deleteOrder(Integer ordersId) {

        // 로그인 한 사람만
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("로그인을 먼저 해 주세요.", HttpStatus.FORBIDDEN);
        }

        int userId = principal.getUserId();

        Orders orders = ordersRepository.findById(ordersId);
        productRepository.productQtyReupdate(orders);

        // 주문 정보 삭제
        ordersRepository.deleteById(ordersId);

        return "redirect:/ordersList/" + userId;
    }
}
