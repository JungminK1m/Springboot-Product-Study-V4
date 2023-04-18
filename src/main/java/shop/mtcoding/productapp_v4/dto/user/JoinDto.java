package shop.mtcoding.productapp_v4.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDto {

    private String userName;
    private Integer userPassword;
    private String userEmail;

}
