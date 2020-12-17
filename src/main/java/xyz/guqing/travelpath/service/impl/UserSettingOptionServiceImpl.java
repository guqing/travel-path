package xyz.guqing.travelpath.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import xyz.guqing.travelpath.exception.MissingPropertyException;
import xyz.guqing.travelpath.mapper.UserSettingOptionMapper;
import xyz.guqing.travelpath.model.entity.UserSettingOption;
import xyz.guqing.travelpath.model.enums.PropertyEnum;
import xyz.guqing.travelpath.service.UserSettingOptionService;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

import java.util.Optional;

/**
 * 用户配置项服务实现类
 *
 * @author guqing
 * @date 2020-12-17
 */
@Service
public class UserSettingOptionServiceImpl extends ServiceImpl<UserSettingOptionMapper, UserSettingOption> implements UserSettingOptionService {
    @Override
    public Object getByKeyOfNullable(String key) {
        return getByKey(key).orElse(null);
    }

    @Override
    public Object getByKeyOfNonNull(String key) {
        return getByKey(key).orElseThrow(() -> new MissingPropertyException("You have to config " + key + " setting"));
    }

    @Override
    public Optional<Object> getByKey(String key) {
        Assert.hasText(key, "Option key must not be blank");
        Long currentUserId = SecurityUserHelper.getCurrentUserId();
        // 根据分组和key查询
        LambdaQueryWrapper<UserSettingOption> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(UserSettingOption::getOptionKey, key)
                .eq(UserSettingOption::getUserId, currentUserId);
        UserSettingOption settingOption = getOne(queryWrapper);
        if (settingOption != null) {
            return Optional.of(settingOption.getOptionValue());
        }
        return Optional.empty();
    }

    @Override
    public <T> T getByKeyOrDefault(String key, Class<T> valueType, T defaultValue) {
        return getByKey(key, valueType).orElse(defaultValue);
    }

    @Override
    public <T> Optional<T> getByKey(String key, Class<T> valueType) {
        return getByKey(key).map(value -> PropertyEnum.convertTo(value.toString(), valueType));
    }

    @Override
    public <T> T getByPropertyOrDefault(PropertyEnum property, Class<T> propertyType, T defaultValue) {
        Assert.notNull(property, "Setting property must not be null");

        return getByProperty(property, propertyType).orElse(defaultValue);
    }

    @Override
    public <T> T getByPropertyOrDefault(PropertyEnum property, Class<T> propertyType) {
        return getByProperty(property, propertyType).orElse(property.defaultValue(propertyType));
    }

    @Override
    public <T> Optional<T> getByProperty(PropertyEnum property, Class<T> propertyType) {
        return getByProperty(property).map(propertyValue -> PropertyEnum.convertTo(propertyValue.toString(), propertyType));
    }

    @Override
    public Optional<Object> getByProperty(PropertyEnum property) {
        Assert.notNull(property, "Setting property must not be null");

        return getByKey(property.getValue());
    }
}
