package shop.mtcoding.productapp_v4.model.user;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private Timestamp createdAt;
    private String role;

    public User(String userName, String userPassword, String userEmail, String role) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.role = role;
        // 거르기 위해서 사용!! -> JoinDto > toEntity에서 적어준 값으로 들어감
        // 예시 ) this.role = "USER"; // 무조건 USER로 값이 들어감
    }
}
