package xyz.guqing.travelpath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.guqing.travelpath.entity.params.RegisterParam;
import xyz.guqing.travelpath.entity.params.UserParam;
import xyz.guqing.travelpath.service.UserService;

/**
 * user service测试类<br>
 *
 * @author guqing
 * @date 2019-10-18 21:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testUpdateLastLoginTime() {
        userService.updateLoginTime(1, "127.0.0.1");
    }

    @Test
    public void testUsernameExists() {
        System.out.println(userService.checkUserExistsByUsername("guqin"));
    }
}
