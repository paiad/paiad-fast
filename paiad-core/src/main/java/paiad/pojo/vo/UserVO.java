package paiad.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户信息返回对象（UserVO）
 */
@Data
public class UserVO implements Serializable {

    private Long userId;

    private String userName;

    private String nickName;

    private Integer gender;

    private String avatar;

    private List<String> roles;

    private List<String> buttons;

    private static final long serialVersionUID = 1L;
}
