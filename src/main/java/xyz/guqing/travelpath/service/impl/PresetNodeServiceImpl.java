package xyz.guqing.travelpath.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.mapper.PresetNodeMapper;
import xyz.guqing.travelpath.model.entity.PresetNode;
import xyz.guqing.travelpath.service.PresetNodeService;

import java.util.List;

/**
 * @author guqing
 * @date 2020-10-19
 */
@Service
public class PresetNodeServiceImpl extends ServiceImpl<PresetNodeMapper, PresetNode> implements PresetNodeService {
    @Override
    public void createOrUpdate(Long presetId, List<PresetNode> checkpoints) {
        checkpoints.forEach(presetNode -> {
            presetNode.setPresetId(presetId);
        });
        saveBatch(checkpoints);
    }
}
