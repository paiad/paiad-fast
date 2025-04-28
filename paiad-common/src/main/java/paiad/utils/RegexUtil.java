package paiad.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    // 手机号正则校验规则
    private static final String PHONE_REGEX = "^1[3-9]\\d{9}$";
    // 邮箱正则校验规则
    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    // 6位数字验证码正则校验规则
    private static final String CODE_REGEX = "^\\d{6}$";

    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern CODE_PATTERN = Pattern.compile(CODE_REGEX);

    /**
     * 验证手机号格式是否正确
     * @param phoneNumber 需要验证的手机号
     * @return 返回true表示格式正确，false表示格式错误
     */
    public static boolean isValidPhoneNum(String phoneNumber) {
        return isValid(phoneNumber, PHONE_PATTERN);
    }

    /**
     * 验证邮箱格式是否正确
     * @param email 需要验证的邮箱地址
     * @return 返回true表示格式正确，false表示格式错误
     */
    public static boolean isValidEmail(String email) {
        return isValid(email, EMAIL_PATTERN);
    }

    /**
     * 验证6位数字验证码格式是否正确
     * @param code 需要验证的6位数字验证码
     * @return 返回true表示格式正确，false表示格式错误
     */
    public static boolean isValidCode(String code) {
        return isValid(code, CODE_PATTERN);
    }

    /**
     * 通用的验证方法，用于校验字符串是否匹配正则表达式
     * @param input 需要验证的输入字符串
     * @param pattern 用于匹配的正则表达式Pattern对象
     * @return 返回true表示匹配，false表示不匹配
     */
    private static boolean isValid(String input, Pattern pattern) {
        if (input == null || input.isEmpty()) {
            return false; // 可以根据需要决定是否允许空值或null
        }
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}

