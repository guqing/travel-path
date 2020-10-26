package xyz.guqing.travelpath.model.dto;

import lombok.Data;
import xyz.guqing.travelpath.model.dos.DeployPlanDO;
import xyz.guqing.travelpath.model.entity.PresetNode;
import xyz.guqing.travelpath.model.support.OutputConverter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author guqing
 * @date 2020-10-23
 */
@Data
public class DeployPlanDTO implements OutputConverter<DeployPlanDTO, DeployPlanDO> {
    private Long id;

    private Integer count;
    /**
     * 方案名称
     */
    private String name;
    /**
     * 方案描述
     */
    private String description;

    private LocalDateTime createTime;

    /**
     * 卡口位置
     */
    private List<PresetNode> checkpoints;

    @Override
    public <T extends DeployPlanDTO> T convertFrom(DeployPlanDO deployPlanDO) {
        this.checkpoints = deployPlanDO.getPresetNodes();
        return (T)this;
    }
}
