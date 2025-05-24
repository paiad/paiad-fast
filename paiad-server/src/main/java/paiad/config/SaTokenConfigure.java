package paiad.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  注册 Sa-Token 拦截器
 * */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handler -> {
            SaRouter.match("/**")
                    .notMatch("/auth/login")
                    .notMatch("/auth/register")
                    .notMatch("/auth/test")
                    .notMatch("/mqtt/**")
                    .notMatch("/doc.html")
                    .notMatch("/webjars/**")
                    .notMatch("/swagger-resources/**")
                    .notMatch("/v3/api-docs/**")
                    .check(r -> StpUtil.checkLogin());

        })).addPathPatterns("/**");
    }
}
