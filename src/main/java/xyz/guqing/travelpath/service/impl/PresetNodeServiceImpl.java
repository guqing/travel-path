package xyz.guqing.travelpath.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(rollbackFor = Exception.class)
    public void createOrUpdate(Long presetId, List<PresetNode> checkpoints) {
        // 先删除
        deleteByPresetId(presetId);
        checkpoints.forEach(presetNode -> {
            presetNode.setPresetId(presetId);
        });
        saveBatch(checkpoints);
    }

    private void deleteByPresetId(Long presetId) {
        LambdaQueryWrapper<PresetNode> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(PresetNode::getPresetId, presetId);
        remove(queryWrapper);
    }
}
