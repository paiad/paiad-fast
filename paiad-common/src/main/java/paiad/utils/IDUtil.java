package paiad.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author:AD
 * @Date:2024/08/19, 11:15:12
 */
public class IDUtil {
    /**
     * 生成六位验证码
     */
    public static String getVerifyCode() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000, 999999));
    }
}
