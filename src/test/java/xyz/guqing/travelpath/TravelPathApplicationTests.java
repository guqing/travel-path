package xyz.guqing.travelpath;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.guqing.travelpath.entity.model.Presetpoint;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelPathApplicationTests {

	@Test
	public void contextLoads() {
		List<Presetpoint> presetpointList = new ArrayList<>();
		Presetpoint presetpoint = new Presetpoint();
		presetpoint.setLat("40.02301117097036");
		presetpoint.setLng("116.21710348773304");
		presetpointList.add(presetpoint);
		System.out.println(JSONArray.toJSONString(presetpointList));
	}

}
