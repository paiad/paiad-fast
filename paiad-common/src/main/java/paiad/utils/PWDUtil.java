package paiad.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class PWDUtil {

    // 使用 SHA-256 算法加密密码
    public static String encryptPwd(String password) {
        try {
            // 创建 MessageDigest 实例，指定 SHA-256 算法
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // 对密码进行加密
            md.update(password.getBytes());
            // 获取加密后的字节数组
            byte[] encryptedBytes = md.digest();
            // 将字节数组转换成十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : encryptedBytes) {
                sb.append(String.format("%02x", b));
            }
            // 返回加密后的字符串
            return sb.toString();
        }catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
        return password;
    }

    public static void main(String[] args) {
            // 加密密码
            String encryptedPwd = encryptPwd("admin");
            // 打印加密后的密码
            System.out.println("加密后的密码: " + encryptedPwd);
    }
}
