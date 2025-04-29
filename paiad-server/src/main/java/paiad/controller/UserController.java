package paiad.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import paiad.pojo.dto.UserDTO;
import paiad.service.IUserService;

@RestController
@RequestMapping("/user/")
@Tag(name = "User接口文档")
public class UserController {
    @Resource
    private IUserService userService;

    @Resource
    private HttpServletRequest request;

    @PostMapping("register")
    @Operation(summary = "注册")
    public SaResult register(@RequestBody @Valid UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @PostMapping("login")
    @Operation(summary = "登录")
    public SaResult login(@RequestBody @Valid UserDTO userDTO) {
        return userService.login(userDTO, request.getRemoteAddr());
    }

    @GetMapping("isLogin")
    @Operation(summary = "是否登录")
    public SaResult isLogin() {
        return userService.isLogin();
    }

    @GetMapping("info")
    @Operation(summary = "用户信息")
    public SaResult getUserInfo() {
        System.out.println(StpUtil.getPermissionList());
        Object userInfo = StpUtil.getSession().get("userInfo");
        return userService.getUserInfo(userInfo);
    }

}
