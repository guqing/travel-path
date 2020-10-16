package xyz.guqing.travelpath.model.params;

import lombok.Data;
import xyz.guqing.travelpath.model.entity.Menu;
import xyz.guqing.travelpath.model.support.InputConverter;

/**
 * @author guqing
 * @date 2020-06-04
 */
@Data
public class MenuQuery implements InputConverter<Menu> {
    private Long orderIndex;
    private String type;
}
