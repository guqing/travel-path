package xyz.guqing.travelpath;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.guqing.travelpath.entity.model.PresetScheme;
import xyz.guqing.travelpath.entity.model.Presetpoint;
import xyz.guqing.travelpath.service.PresetSchemeService;
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
	private PresetSchemeService presetSchemeService;
	@Test
	public void contextLoads() {
		System.out.println(presetSchemeService.listSechemeByPage(1,10,1));
	}

}
