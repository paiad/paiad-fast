package paiad.utils;

import cn.hutool.jwt.JWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JWTUtil extends cn.hutool.jwt.JWTUtil {
    /**
     * key对应密钥,这里可以切换为自己的私钥
     */
    private static String key = "hash_by_ad";
    public static String createToken(Map<String, Object> payload) {
        return cn.hutool.jwt.JWTUtil.createToken(null, payload, key.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 获得token
     * */
    public static String getToken(){
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return request.getHeader("Authorization");
    }

    /**
     * 从Token，解出userId
     * */
    public static String getUserId(){
        String token = getToken();
        JWT jwt = JWT.of(token);
        return jwt.getPayload("userId").toString();
    }
    public static Map<String,String> getTokenMap(String token){
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        return map;
    }

}
