package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xyz.guqing.travelpath.entity.model.User;
import xyz.guqing.travelpath.entity.model.UserExample;
import xyz.guqing.travelpath.mapper.UserMapper;

import java.util.List;

/**
 * @author guqing
 * @date 2019/8/9
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserByUsername(String username){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);

        if (CollectionUtils.isEmpty(userList)){
            return null;
        }
        return userList.get(0);
    }
}
