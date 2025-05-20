package paiad.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVO {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String role;
    private String permission;
    private String nickname;
    private String avatar;
    private LocalDateTime lastLoginTime;
}
