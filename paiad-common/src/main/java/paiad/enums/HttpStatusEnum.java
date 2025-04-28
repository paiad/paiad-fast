package paiad.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author:AD
 * @Date:2024/08/07, 00:10:13
 */
@Getter
@AllArgsConstructor
public enum HttpStatusEnum {
    /**
     * http状态码枚举所有状态码注解
     */
    SUCCESS(200, "操作成功"),
    CREATED(201, "请求已经成功处理，并创建了资源"),

    UNAUTHORIZED(401, "未授权"),
    NO_AUTH(403, "用户权限不够"),
    NOT_FOUND(404, "资源未找到"),

    FAIL(500, "操作失败"),
    /**
     * 用户状态
     * */
    ERROR_ACCOUNT_OR_PASSWORD(500, "账号或密码错误"),
    NOT_ENOUGH_AUTH(401, "用户权限不够"),
    LOGIN_TIME_OVER(401, "用户登录已超时，请重新登录"),
    TOKEN_IS_NULL(401, "token为空, 请先登录获取token"),
    ERROR_TOKEN(401, "token错误,请登录获取正确的token"),
    ERROR_PASSWORD(500,"原密码错误，请重新输入密码"),
    NO_ACCOUNT(500,"账号不存在"),
    USERNAME_ALREADY_EXIST(500,"该用户名已存在"),
    NOT_EXIST_USER(500,"不存在对应的用户");

    private final Integer code;
    private final String message;
}
