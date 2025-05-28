package paiad.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TokenInfo implements Serializable {
    // 令牌(short token)
    private String token;

    //刷新令牌(long token)
    private String refreshToken;

    private static final long serialVersionUID = 1L;
}
