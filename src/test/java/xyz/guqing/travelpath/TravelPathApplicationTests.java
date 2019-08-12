package xyz.guqing.travelpath;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.guqing.travelpath.entity.model.Presetpoint;
import xyz.guqing.travelpath.service.RoleService;
import xyz.guqing.travelpath.utils.JwtTokenUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelPathApplicationTests {
	@Autowired
	private JwtTokenUtil tokenUtil;
	@Autowired
	private RoleService roleService;
	@Test
	public void contextLoads() {
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndXFpbmciLCJJRCI6MSwiZXhwIjoxNTY1NDkzNDk2LCJjcmVhdGVkIjoxNTY1NDkxNjk2MTg5fQ.Yku0lgXYy0Ik0CIqZeS5H_DJSe-fNGBeB94xHz0YbbtARszZRVisZZl8Ccv6CmWFqbiWX23ooWVDEYb4wIieiw";
		String usernameFromToken = tokenUtil.getUsernameFromToken(token);
		System.out.println(usernameFromToken);
	}

}
