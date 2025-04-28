package paiad.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author:AD
 * @Date:2024/08/06, 23:48:39
 */
@Getter
@AllArgsConstructor
public enum IdentityEnum {
    ADMIN("admin","管理员"),
    USER("user","用户");
    private final String identity;
    private final String chIdentity;
}
