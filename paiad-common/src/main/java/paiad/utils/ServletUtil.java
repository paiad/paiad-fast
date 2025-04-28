package paiad.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 客户端工具类
 */
public class ServletUtil {
    /**
     * 用于访问当前的请求属性
     * */
    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 获取请求头Request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取String参数
     * http://www.example.com/page?param1=value1&param2=value2
     * String value1 = getRequest().getParameter("param1"); // 获取value1
     * String value2 = getRequest().getParameter("param2"); // 获取value2
     */
    public static String getParameter(String param) {
        return getRequest().getParameter(param);
    }

}
