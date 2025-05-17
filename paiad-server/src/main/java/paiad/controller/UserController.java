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
import paiad.pojo.dto.UserDTO;
import paiad.service.IUserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth/")
@Tag(name = "User接口文档")
public class UserController {
    @Resource
    private IUserService userService;

    @Resource
    private HttpServletRequest request;

    @PostMapping("register")
    @Operation(summary = "用户注册")
    public SaResult register(@RequestBody @Valid UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @PostMapping("login")
    @Operation(summary = "用户登录")
    public Map login(@RequestBody @Valid UserDTO userDTO) {
        Map map1 = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        map.put("token","123456");
        map.put("refreshToken","123456");

        map1.put("data",map);
        return map1;
    }

    @GetMapping("isLogin")
    @Operation(summary = "是否登录")
    public SaResult isLogin() {
        return userService.isLogin();
    }

    @GetMapping("getUserInfo")
    @Operation(summary = "用户信息")
    public SaResult getUserInfo() {
        Object userInfo = StpUtil.getSession().get("userInfo");
        return userService.getUserInfo(userInfo);
    }

    @GetMapping("permission")
    @Operation(summary = "用户权限")
    public SaResult getPermission() {
        return userService.getPermission();
    }

    @GetMapping("role")
    @Operation(summary = "用户角色")
    public SaResult getRole() {
        return userService.getRole();
    }

    @PostMapping("logout")
    @Operation(summary = "用户登出")
    public SaResult logout() {
        return userService.logout();
    }

    @GetMapping("/test")
    @Operation(summary = "用户测试(No Token)")
    public SaResult test() {
        String formattedTime = DateUtil.now(); // 默认格式：yyyy-MM-dd HH:mm:ss
        System.out.println("时间：" +  formattedTime +"\n请求中......");
        return SaResult.ok();
    }

}
