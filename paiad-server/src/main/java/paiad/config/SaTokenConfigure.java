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
            SaRouter
                    .match("/**")
                    .notMatch("/user/login")
                    .notMatch("/user/register")
                    .notMatch("/doc.html") // Knife4j 文档页面
                    .notMatch("/webjars/**") // Knife4j 静态资源
                    .notMatch("/swagger-resources/**") // Swagger 资源
                    .notMatch("/v3/api-docs/**") // OpenAPI 文档接口
                    .check(r -> StpUtil.checkLogin()); // 校验是否登录

            // 可选：权限校验示例
//            SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
//            SaRouter.match("/admin/**", r -> StpUtil.checkRoleOr("admin", "super-admin"));
        })).addPathPatterns("/**");
    }
}
