package paiad.service;


import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.extension.service.IService;
import paiad.pojo.dto.LoginDTO;
import paiad.pojo.po.User;


public interface IAuthService extends IService<User> {
    SaResult register(LoginDTO userDTO);
    SaResult login(LoginDTO userDTO, String ipAddress);
    SaResult refreshToken(String refreshToken);
    SaResult isLogin();
    SaResult getUserInfo();
    SaResult getPermission();
    SaResult getRole();
    SaResult logout();
}
