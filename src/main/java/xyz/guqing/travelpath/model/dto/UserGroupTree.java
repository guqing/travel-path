package xyz.guqing.travelpath.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.guqing.travelpath.model.entity.UserGroup;
import xyz.guqing.travelpath.model.support.Tree;

/**
 * @author guqing
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserGroupTree extends Tree<UserGroup> {
    private Integer orderIndex;
}
