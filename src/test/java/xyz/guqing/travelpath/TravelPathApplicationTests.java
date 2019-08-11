package xyz.guqing.travelpath;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.guqing.travelpath.entity.model.Presetpoint;
import xyz.guqing.travelpath.utils.JwtTokenUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelPathApplicationTests {
	@Autowired
	private JwtTokenUtil tokenUtil;
	@Test
	public void contextLoads() {
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndXFpbmciLCJpZCI6MSwiZXhwIjoxNTY1NDQ2MTQwLCJjcmVhdGVkIjoxNTY1NDQ0MzQwMjcxfQ.EMo2Ki8wEA3621SM97IYo6HYMcWGbO5tODyTTqJsZ9BJ_DSo8kVmV6i9c9AudtdRb84C66fcxvm6lK7bvWV2Pg";
		Integer userId = tokenUtil.getUserIdFromToken(token);
		System.out.println(userId);
	}

}
