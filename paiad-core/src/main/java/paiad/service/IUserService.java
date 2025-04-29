package paiad.service;


import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.extension.service.IService;
import paiad.pojo.dto.UserDTO;
import paiad.pojo.po.User;

import java.time.LocalDateTime;


public interface IUserService extends IService<User> {
    SaResult register(UserDTO userDTO);
    SaResult login(UserDTO userDTO, String ipAddress);
    SaResult isLogin();
}
