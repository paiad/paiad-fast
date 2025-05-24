package paiad.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import paiad.handler.StringListTypeHandler;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "paiad_user", autoResultMap = true)
public class User {

    @TableField("userId")
    private Long userId;

    @TableField("userName")
    private String userName;

    @TableField("password")
    private String password;

    @TableField("email")
    private String email;

    @TableField("phone")
    private String phone;

    @TableField(value = "roles", typeHandler = StringListTypeHandler.class)
    private List<String> roles;

    @TableField(value = "permissions", typeHandler = StringListTypeHandler.class)
    private List<String> permissions;

    @TableField(value = "buttons", typeHandler = StringListTypeHandler.class)
    private List<String> buttons;

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
