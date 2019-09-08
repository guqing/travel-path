package xyz.guqing.travelpath;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.TableStyle;
import io.jsonwebtoken.Claims;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.model.PresetScheme;
import xyz.guqing.travelpath.entity.vo.PresetPointExcelVO;
import xyz.guqing.travelpath.entity.vo.PresetSchemeExcelVO;
import xyz.guqing.travelpath.service.PresetSchemeService;
import xyz.guqing.travelpath.utils.JwtTokenUtil;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndXFpbmciLCJJRCI6MSwiZXhwIjoxNTY3MTUzNjg0LCJjcmVhdGVkIjoxNTY3MTUzNjI0NDg0fQ.243dXzwT4QJ2Pj2DMuBVYT3YH3gKqPcpCG6HRMTtgteAS6EddBi0jjKbTSLP0bXIhiQEFRFz4Nzs91I0CO7_7w";
		Claims claimsFromToken = tokenUtil.getClaimsFromToken(token);
		System.out.println(claimsFromToken.getSubject());
	}

	@Test
	public void testWriteExcel() throws IOException {
		OutputStream out = new FileOutputStream("F:/预设卡口方案上传模板.xlsx");
		System.out.println(out);
		ExcelWriter writer = EasyExcelFactory.getWriter(out);
		System.out.println(writer);
		Sheet sheet1 = new Sheet(1, 1, PresetSchemeExcelVO.class);
		sheet1.setSheetName("预设卡口方案");
		sheet1.setTableStyle(createTableStyle());

		List<PresetSchemeExcelVO> presetSchemes = new ArrayList<>();
		PresetSchemeExcelVO presetSchemeExcelDTO = new PresetSchemeExcelVO();
		presetSchemeExcelDTO.setId(1L);
		presetSchemeExcelDTO.setName("测试写入第一条方案");
		presetSchemeExcelDTO.setDescription("没啥好说的");
		presetSchemes.add(presetSchemeExcelDTO);
		writer.write(presetSchemes, sheet1);

		List<PresetPointExcelVO> presetPointExcelDTOList = new ArrayList<>();
		PresetPointExcelVO presetPointExcelDTO = new PresetPointExcelVO();
		presetPointExcelDTO.setId(1L);
		presetPointExcelDTO.setLat("40.03262649351757");
		presetPointExcelDTO.setLng("116.2124837757744");
		presetPointExcelDTO.setPreid(4L);
		presetPointExcelDTOList.add(presetPointExcelDTO);

		Sheet sheet2 = new Sheet(2, 1, PresetPointExcelVO.class);
		sheet2.setSheetName("预设卡口坐标点");
		sheet2.setAutoWidth(true);
		writer.write(presetPointExcelDTOList, sheet2);

		writer.finish();
		out.close();
	}

	public TableStyle createTableStyle() {
		TableStyle tableStyle = new TableStyle();
		// 设置表头样式
		Font headFont = new Font();
		// 字体是否加粗
		headFont.setBold(true);
		// 字体大小
		headFont.setFontHeightInPoints((short)12);
		// 字体
		headFont.setFontName("宋体");
		tableStyle.setTableHeadFont(headFont);

		// 背景色
		tableStyle.setTableHeadBackGroundColor(IndexedColors.GREY_25_PERCENT);
		return tableStyle;
	}

	@Test
	public void testSecurityHelper() {
		MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
		Integer userId = user.getId();
		System.out.println(userId);
	}

	@Test
	public void testGetPresetScheme() {
		PresetScheme schemeById = presetSchemeService.getSchemeById(11L);
		System.out.println(schemeById);
	}
}
