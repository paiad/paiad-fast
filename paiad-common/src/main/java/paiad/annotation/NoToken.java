package paiad.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  这里的@NoToken注解仅针对于日志文件。对于接口的真正放行 1. WebWvcConfig放行 2. OperatorLogAspect放行
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoToken {
}
