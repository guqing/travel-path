package xyz.guqing.travelpath.entity.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Component;
import xyz.guqing.travelpath.entity.dto.MailOption;
import xyz.guqing.travelpath.entity.model.Optional;
import xyz.guqing.travelpath.mapper.MailOptionMapper;

import java.util.*;

/**
 * 将Optional对象转换未MailOption<br>
 *
 * @author guqing
 * @date 2019-10-27 15:31
 */
@Data
@Component
public class MailOptionConvert {
    private final MailOptionMapper mailOptionMapper;

    @Autowired
    public MailOptionConvert(MailOptionMapper mailOptionMapper) {
        this.mailOptionMapper = mailOptionMapper;
    }

    public MailOption getMailOption() {
        List<Optional> options = mailOptionMapper.getMailOption();
        System.out.println(options);
        // 将查询出的key-value键值对象遍历存储到一个map中在构建MailOption拷贝属性
        Map<String, Object> optionMap = new HashMap<>(8);
        for(Optional option: options) {
            optionMap.put(option.getKey(), option.getValue());
        }
        JSON json = (JSON) JSON.toJSON(optionMap);
        MailOption mailOption = JSONObject.toJavaObject(json, MailOption.class);
        System.out.println(mailOption);
        return mailOption;
    }
}
