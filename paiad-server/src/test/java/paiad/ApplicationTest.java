package paiad;

import cn.hutool.crypto.digest.BCrypt;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import paiad.service.IAuthService;

@SpringBootTest
public class ApplicationTest {

    @Resource
    private IAuthService userService;

    @Test
    public void testEncodePasswordWithEmptyInput() {
        String rawPassword = "123456";
        System.out.println(BCrypt.hashpw(rawPassword, BCrypt.gensalt()));
    }

}
