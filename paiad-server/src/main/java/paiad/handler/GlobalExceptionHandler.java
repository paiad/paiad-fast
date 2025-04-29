package paiad.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  登录异常捕获
 *  401 警告
 * */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotLoginException.class)
    public SaResult handleNotLoginException(NotLoginException e) {
        String message = switch (e.getType()) {
            case NotLoginException.NOT_TOKEN -> "未提供 Token";
            case NotLoginException.INVALID_TOKEN -> "无效的 Token";
            case NotLoginException.TOKEN_TIMEOUT -> "Token 已过期";
            default -> "当前会话未登录";
        };
        return SaResult.error(message).setCode(401);
    }
}
