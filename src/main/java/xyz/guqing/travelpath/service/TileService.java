package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.model.Tiles;
import xyz.guqing.travelpath.entity.model.TilesExample;
import xyz.guqing.travelpath.mapper.TilesMapper;

import java.util.List;

/**
 * 地图瓦片业务逻辑
 *
 * @author guqin
 * @date 2019-08-06 7:04
 */
@Service
@CacheConfig(cacheNames = "tileService")
public class TileService {
	private TilesMapper tilesMapper;

	@Autowired
	public TileService(TilesMapper tilesMapper) {
		this.tilesMapper = tilesMapper;
	}

	@Cacheable
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
		System.out.println("---->瓦片查询数据库");
		return new Tiles();
	}
}
