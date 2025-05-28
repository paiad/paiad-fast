package paiad.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.date.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import paiad.pojo.dto.LoginDTO;
import paiad.service.IAuthService;

import java.util.Map;

@RestController
@RequestMapping("/auth/")
@Tag(name = "Auth接口文档")
public class AuthController {
    @Resource
    private IAuthService authService;

    @Resource
    private HttpServletRequest request;

    @PostMapping("register")
    @Operation(summary = "用户注册")
    public SaResult register(@RequestBody @Valid LoginDTO loginDTO) {
        return authService.register(loginDTO);
    }

    @PostMapping("login")
    @Operation(summary = "用户登录")
    public SaResult login(@RequestBody @Valid LoginDTO loginDTO) {
        return authService.login(loginDTO, request.getRemoteAddr());
    }

    @PostMapping("refresh")
    @Operation(summary = "刷新Token")
    public SaResult refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return authService.refreshToken(refreshToken);
    }


    @GetMapping("isLogin")
    @Operation(summary = "是否登录")
    public SaResult isLogin() {
        return authService.isLogin();
    }

    @GetMapping("getUserInfo")
    @Operation(summary = "用户信息")
    public SaResult getUserInfo() {
        return authService.getUserInfo();
    }

    @GetMapping("permission")
    @Operation(summary = "用户权限")
    public SaResult getPermission() {
        return authService.getPermission();
    }

    @GetMapping("role")
    @Operation(summary = "用户角色")
    public SaResult getRole() {
        return authService.getRole();
    }

    @PostMapping("logout")
    @Operation(summary = "用户登出")
    public SaResult logout() {
        return authService.logout();
    }

    @GetMapping("test")
    @Operation(summary = "用户测试(No Token)")
    public SaResult test() {
        String formattedTime = DateUtil.now(); // 默认格式：yyyy-MM-dd HH:mm:ss
        System.out.println("时间：" +  formattedTime +"\n请求中......");
        return SaResult.ok();
    }

}
