package xyz.guqing.travelpath.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.model.Log;
import xyz.guqing.travelpath.entity.model.LogExample;
import xyz.guqing.travelpath.mapper.LogMapper;

import java.util.Date;
import java.util.List;

/**
 * 日志service
 *
 * @author guqing
 * @date 2019-08-30 14:03
 */
@Service
public class LogService {
	private LogMapper logMapper;

	@Autowired
	public LogService(LogMapper logMapper) {
		this.logMapper = logMapper;
	}

	public void saveLog(Log log) {
		log.setCreateTime(new Date());
		log.setModifyTime(new Date());
		logMapper.insert(log);
	}

	public PageInfo<Log> listLatestLogByPage(Integer current, Integer pageSize, Integer userId) {
		PageHelper.startPage(current, pageSize, "create_time desc");
		LogExample logExample = new LogExample();
		LogExample.Criteria criteria = logExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<Log> logs = logMapper.selectByExample(logExample);
		return new PageInfo<>(logs);
	}
}
