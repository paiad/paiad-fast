package paiad.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paiad.mapper.UserMapper;
import paiad.pojo.dto.UserDTO;
import paiad.pojo.po.User;
import paiad.pojo.vo.UserVO;
import paiad.service.IUserService;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 注册用户
     */
    public SaResult register(UserDTO userDTO) {
        // 检查用户名是否已存在
        User existingUser = findByUsername(userDTO.getUsername());
        if (existingUser != null) {
            return SaResult.error("用户名已存在");
        }

        // 创建新用户
        User user = new User();
        user.setId(generateId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(encodePassword(userDTO.getPassword())); // 加密密码
        user.setStatus(1);
        userMapper.insert(user);

        return SaResult.ok("注册成功");
    }

    /**
     * 用户登录
     */
    public SaResult login(UserDTO userDTO, String ipAddress) {
        // 根据用户名查询用户
        User user = findByUsername(userDTO.getUsername());
        if (user == null) {
            return SaResult.error("用户不存在");
        }

        // 校验密码
        if (!checkPassword(userDTO.getPassword(), user.getPassword())) {
            return SaResult.error("密码错误");
        }

        // 执行登录
        StpUtil.login(user.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        //登录成功后，将用户的信息放入会话中，方便全局调用
        StpUtil.getSession().set("userInfo", user);

        // 更新登录信息
        updateLoginInfo(user.getId(), ipAddress, LocalDateTime.now());

        // 返回 Token 信息
        return SaResult.data(tokenInfo);
    }

    /**
     * 判断当前用户是否登录
     * */
    public SaResult isLogin(){
        return SaResult.data("当前会话是否登录：" + StpUtil.isLogin());
    }


    /**
     * 获取当前用户信息
     * */
    public SaResult getUserInfo(Object userInfo){
        User user = (User) userInfo;
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return SaResult.data(userVO);
    }








    /*********************************************private method*******************************************************/
    /**
     * 根据用户名查询用户
     */
    private User findByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    /**
     * 校验密码
     */
    private boolean checkPassword(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }

    /**
     * 加密密码
     */
    private String encodePassword(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    /**
     * 更新登录信息（IP 和登录时间）
     */
    private void updateLoginInfo(Long userId, String ipAddress, LocalDateTime loginTime) {
        User user = new User();
        user.setId(userId);
        user.setIpAddress(ipAddress);
        user.setLastLoginTime(loginTime);
        userMapper.updateById(user);
    }

    /**
     * 使用雪花算法生成唯一 ID
     */
    private Long generateId() {
        long workerId = 1L;
        long datacenterId = 1L;
        return IdUtil.getSnowflake(workerId, datacenterId).nextId();
    }
}
