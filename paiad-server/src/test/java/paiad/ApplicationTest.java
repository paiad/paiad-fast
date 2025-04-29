package paiad;

import cn.hutool.crypto.digest.BCrypt;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import paiad.service.IUserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApplicationTest {

    @Resource
    private IUserService userService;

    @Test
    public void testEncodePasswordWithEmptyInput() {
        String rawPassword = "123456";
        System.out.println(BCrypt.hashpw(rawPassword, BCrypt.gensalt()));
    }

}
