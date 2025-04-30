package paiad.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Component;
import paiad.pojo.po.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        User user = (User) StpUtil.getSession().get("userInfo");
        if (user == null || user.getPermission() == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(user.getPermission().split(","));
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        User user = (User) StpUtil.getSession().get("userInfo");
        if (user == null || user.getRole() == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(user.getRole().split(","));
    }

}
