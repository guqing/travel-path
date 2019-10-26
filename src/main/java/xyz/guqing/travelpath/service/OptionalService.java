package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.model.Optional;
import xyz.guqing.travelpath.entity.model.OptionalExample;
import xyz.guqing.travelpath.mapper.OptionalMapper;

import java.util.List;

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

    public Optional getValueByGroupNameAndKey(String groupName, String key) {
        OptionalExample example = new OptionalExample();
        OptionalExample.Criteria criteria = example.createCriteria();
        criteria.andGroupNameEqualTo(groupName);
        criteria.andKeyEqualTo(key);

        List<Optional> optionals = optionalMapper.selectByExample(example);
        if(!optionals.isEmpty()) {
            return optionals.get(0);
        }
        return null;
    }
}
