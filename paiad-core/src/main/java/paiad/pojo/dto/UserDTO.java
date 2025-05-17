package paiad.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class UserDTO {
    @NotBlank(message = "用户名不能为空")
    @JsonProperty("userName")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
