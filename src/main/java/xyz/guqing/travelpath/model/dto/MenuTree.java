package xyz.guqing.travelpath.model.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.guqing.travelpath.model.entity.Menu;
import xyz.guqing.travelpath.model.support.Tree;

/**
 * @author guqing
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuTree extends Tree<Menu> {
    private String icon;
    private String type;
    private String perms;
}
