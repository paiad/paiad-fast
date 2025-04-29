package paiad.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * paiad-user表
 * */

@TableName(value = "paiad_user",autoResultMap = true)
@Data
public class User {
    @TableField("id")
    private Long id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("email")
    private String email;

    @TableField("phone")
    private String phone;

    @TableField("role")
    private String role;

    @TableField("permission")
    private String permission;

    @TableField("ip_address")
    private String ipAddress;

    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @TableField("status")
    private Integer status;

    @TableField("gender")
    private Integer gender;

    @TableField("nickname")
    private String nickname;

    @TableField("avatar")
    private String avatar;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
