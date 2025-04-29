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
        String message;
        switch (e.getType()) {
            case NotLoginException.NOT_TOKEN:
                message = "未提供 Token";
                break;
            case NotLoginException.INVALID_TOKEN:
                message = "无效的 Token";
                break;
            case NotLoginException.TOKEN_TIMEOUT:
                message = "Token 已过期";
                break;
            default:
                message = "当前会话未登录";
                break;
        }
        return SaResult.error(message).setCode(401);
    }
}
