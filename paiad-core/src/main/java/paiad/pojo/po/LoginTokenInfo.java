package paiad.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LoginTokenInfo implements Serializable {
    private String token;
    private String refreshToken;
}
