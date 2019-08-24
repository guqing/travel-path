package xyz.guqing.travelpath.controller;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.guqing.travelpath.entity.model.Tiles;
import xyz.guqing.travelpath.service.TileService;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 地图瓦片类
 *
 * @author guqin
 * @date 2019-08-06 7:00
 */
@Controller
@RequestMapping("/tile")
public class TileController {
	private static final Logger logger = LoggerFactory.getLogger(TileController.class);

	private TileService tileService;

	@Autowired
	public TileController(TileService tileService) {
		this.tileService = tileService;
	}

	@GetMapping("/get/{z}/{x}/{y}")
	public void getTile(HttpServletResponse response,
						@PathVariable("z") Integer z,
						@PathVariable("x") Integer x,
						@PathVariable("y") Integer y) {
		try {
			Tiles tile = tileService.getTile(z, x, y);
			ServletOutputStream outputStream = response.getOutputStream();
			byte[] bytes = tile.getTileData();
			if(ArrayUtils.isNotEmpty(bytes)) {
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
				BufferedImage read = ImageIO.read(byteArrayInputStream);
				ImageIO.write(read, "png", outputStream);

				byteArrayInputStream.close();
				outputStream.close();
			}
		} catch (Exception e) {
			logger.error("加载地图瓦片出错，errors:{}", e.getMessage());
		}
	}
}
