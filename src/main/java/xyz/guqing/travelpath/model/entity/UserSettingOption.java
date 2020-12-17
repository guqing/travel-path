package xyz.guqing.travelpath.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户设置配置项
 *
 * @author guqing
 * @date 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserSettingOption extends BaseEntity {
    private Long userId;
    private String optionKey;
    private String optionValue;
}

