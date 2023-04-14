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
}
