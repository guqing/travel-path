package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.mapper.OptionalMapper;

/**
 * 系统参数服务类,存储的是key-value的选项<br>
 *
 * @author guqin
 * @date 2019-10-26 21:34
 */
@Service
public class OptionalService {
    private final OptionalMapper optionalMapper;

    @Autowired
    public OptionalService(OptionalMapper optionalMapper) {
        this.optionalMapper = optionalMapper;
    }
}
