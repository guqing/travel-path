package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.model.Log;
import xyz.guqing.travelpath.mapper.LogMapper;

import java.util.Date;

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
}
