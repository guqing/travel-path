package xyz.guqing.travelpath.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.guqing.travelpath.model.entity.PresetNode;

import java.util.List;

/**
 * @author guqing
 * @date 2020-10-19
 */
public interface PresetNodeService extends IService<PresetNode> {
    /**
     * 创建或更新卡口点
     * @param presetId 预设卡口方案id
     * @param checkpoints 卡口点
     */
    void createOrUpdate(Long presetId, List<PresetNode> checkpoints);
}
