package xyz.guqing.travelpath.model.params;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import xyz.guqing.travelpath.model.entity.PresetNode;
import xyz.guqing.travelpath.model.entity.PresetPlan;
import xyz.guqing.travelpath.model.support.InputConverter;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author guqing
 * @date 2020-10-19
 */
@Data
public class PresetPlanParam implements InputConverter<PresetPlan> {
    private Long id;
    @NotBlank(message = "方案名称不能为空")
    private String name;
    private String description;
    @JsonIgnore
    private Integer count;

    private List<PresetNode> checkpoints;

    @Override
    public PresetPlan convertTo() {
        this.count = checkpoints.size();
        return InputConverter.super.convertTo();
    }
}
