package paiad.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import paiad.mapper.UserMapper;
import paiad.pojo.dto.LoginDTO;
import paiad.pojo.po.TokenInfo;
import paiad.pojo.po.User;
import paiad.pojo.vo.UserVO;
import paiad.service.IAuthService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AuthServiceImpl extends ServiceImpl<UserMapper, User> implements IAuthService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 注册用户
     */
    public SaResult register(LoginDTO userDTO) {
        // 检查用户名是否已存在
        User existingUser = findByUsername(userDTO.getUserName());
        if (existingUser != null) {
            return SaResult.error("用户名已存在");
        }

        // 创建新用户
        User user = new User();
        user.setUserId(generateId());
        user.setUserName(userDTO.getUserName());
        user.setGender(0);
        user.setAvatar("https://gravatar.com/avatar/19310963b4cc243140a076f54843d91c");
        user.setPassword(encodePassword(userDTO.getPassword()));
        user.setRoles(Collections.singletonList("user"));//默认 role = 'user'
        user.setPermissions(Collections.singletonList("user:get"));//默认 permission = 'user:get'
        user.setStatus(1);
        userMapper.insert(user);

        return SaResult.ok("注册成功");
    }

    /**
     * 用户登录
     */
    public SaResult login(LoginDTO userDTO, String ipAddress) {
        // 根据用户名查询用户
        User user = findByUsername(userDTO.getUserName());
        if (user == null) {
            return SaResult.error("用户不存在");
        }

        // 校验密码
        if (!checkPassword(userDTO.getPassword(), user.getPassword())) {
            return SaResult.error("账号或密码不正确");
        }

        // 执行登录
        StpUtil.login(user.getUserId());
        log.info("username为:'{}' 已完成登录", userDTO.getUserName());
        String refreshToken = UUID.randomUUID().toString();
        TokenInfo tokenInfo = new TokenInfo(
                StpUtil.getTokenValue(), refreshToken);
        stringRedisTemplate.opsForValue().set("paiad-token:login:refreshToken:" + refreshToken,
                user.getUserId().toString(), 7, TimeUnit.DAYS);

        //登录成功后，将用户的信息放入会话中，方便全局调用
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        StpUtil.getSession().set("userInfo", userVO);

        // 更新登录信息
        updateLoginInfo(user.getUserId(), ipAddress, LocalDateTime.now());

        // 返回 Token 信息
        return SaResult.data(tokenInfo);
    }

    /**
     * 用 freshToken 换新token
     */
    public SaResult refreshToken(String refreshToken) {
        String redisKey = "paiad-token:login:refreshToken:" + refreshToken;
        String userIdStr = stringRedisTemplate.opsForValue().get(redisKey);

        if (userIdStr == null || !userIdStr.matches("^\\d+$")) {
            log.warn("刷新令牌失败，Redis中未找到合法的 userId");
            return SaResult.error("无效的 refreshToken");
        }

        Long userId = Long.parseLong(userIdStr);

        // 登录并生成新 token
        StpUtil.login(userId);
        String newToken = StpUtil.getTokenValue();
        log.info("User:{}, 获得的新Token:{}", userId, newToken);

        return SaResult.data(newToken);
    }


    /**
     * 判断当前用户是否登录
     */
    public SaResult isLogin() {
        return SaResult.data("isLogin:" + StpUtil.isLogin());
    }

    /**
     * 获取当前用户信息
     */
    public SaResult getUserInfo() {
        UserVO userVO = (UserVO) StpUtil.getSession().get("userInfo");
        log.info("{}", userVO);
        return SaResult.data(userVO);
    }

    /**
     * 获取当前用户操作权限
     */
    public SaResult getPermission() {
        List<String> permissionList = StpUtil.getPermissionList();
        return SaResult.data(permissionList);
    }

    /**
     * 获取当前用户角色
     */
    public SaResult getRole() {
        List<String> roleList = StpUtil.getRoleList();
        return SaResult.data(roleList);
    }

    /**
     * 用户登出
     */
    public SaResult logout() {
        UserVO userVO = (UserVO) StpUtil.getSession().get("userInfo");
        log.info("username为:'{}' 已完成登出", userVO.getUserName());
        StpUtil.logout();
        return SaResult.ok("登出成功");
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
        userMapper.update(
                null,
                new LambdaUpdateWrapper<User>()
                        .eq(User::getUserId, userId)
                        .set(User::getIpAddress, ipAddress)
                        .set(User::getLastLoginTime, loginTime)
        );
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
