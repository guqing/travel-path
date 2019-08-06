package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.Tiles;
import xyz.guqing.travelpath.entity.TilesExample;
import xyz.guqing.travelpath.mapper.TilesMapper;

import java.awt.*;
import java.util.List;

/**
 * 地图瓦片业务逻辑
 *
 * @author guqin
 * @date 2019-08-06 7:04
 */
@Service
public class TileService {
	@Autowired
	private TilesMapper tilesMapper;

	public Tiles getTile(Integer z, Integer x, Integer y) {
		TilesExample example = new TilesExample();
		TilesExample.Criteria criteria = example.createCriteria();
		criteria.andZoomLevelEqualTo(z);
		criteria.andTileColumnEqualTo(x);
		criteria.andTileRowEqualTo(y);
		List<Tiles> tiles = tilesMapper.selectByExampleWithBLOBs(example);
		if(tiles != null && tiles.size() >0) {
			return tiles.get(0);
		}

		return null;
	}
}
