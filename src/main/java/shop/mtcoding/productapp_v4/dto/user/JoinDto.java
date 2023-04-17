package shop.mtcoding.productapp_v4.dto.user;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.productapp_v4.model.user.User;

@Getter
@Setter
public class JoinDto {

    private String userName;
    private String userPassword;
    private String userEmail;

    // 회원가입 시에 직접 role을 입력하는 것이 아니기 때문에 toEntity에 적어주자
    public User toEntity() {
        // 이거 왜 안 적지?
        // User user = new User(this.userName, this.userPassword, this.userEmail,
        // "user");
        // return user;
        return new User(this.userName, this.userPassword, this.userEmail, "user");
    }
}
