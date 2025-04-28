package paiad.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TIMEUtil {
    public static LocalDateTime now(){
        // 获取当前的LocalDateTime
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fTime = now.format(formatter);
        //获取格式化后的时间
        return LocalDateTime.parse(fTime, formatter);
    }
}
