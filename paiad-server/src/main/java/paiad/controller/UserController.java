package paiad.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import paiad.pojo.dto.UserDTO;
import paiad.pojo.po.User;
import paiad.service.IUserService;

import java.time.LocalDateTime;

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
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

}
