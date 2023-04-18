package shop.mtcoding.productapp_v4.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.productapp_v4.dto.user.AdminLoginDto;
import shop.mtcoding.productapp_v4.dto.user.LoginDto;
import shop.mtcoding.productapp_v4.model.user.User;
import shop.mtcoding.productapp_v4.model.user.UserRepository;

@Controller
public class UserController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    // 구매자 로그인
    @PostMapping("/login")
    public String login(LoginDto loginDto) {
        User userPS = userRepository.login(loginDto);
        if (userPS != null && userPS.getRole().equals("USER")) {
            session.setAttribute("principal", userPS);

            System.out.println("userName : " + userPS.getUserName());
            System.out.println("userPassword : " + userPS.getUserPassword());
            System.out.println("Role : " + userPS.getRole());
            System.out.println("user 로그인 성공");

            // 로그인 성공
            return "redirect:/product";

        }
        // 로그인 실패
        return "redirect:/loginForm";
    }

    // 관리자 로그인
    @PostMapping("/adminLogin")
    public String adminLogin(AdminLoginDto adminLoginDto) {

        User userPS = userRepository.adminLogin(adminLoginDto);

        if (userPS != null && userPS.getRole().equals("ADMIN")) {

            session.setAttribute("principal", userPS);

            System.out.println("adminName : " + userPS.getUserName());
            System.out.println("adminPassword : " + userPS.getUserPassword());
            System.out.println("Role : " + userPS.getRole());
            System.out.println("admin 로그인 성공");

            // 로그인 성공
            return "redirect:/product";
        }
        // 로그인 실패
        return "redirect:/adminLoginForm";

    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/adminLoginForm")
    public String adminLoginForm() {
        return "user/adminLoginForm";
    }

    @GetMapping("/logout")
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }

}
