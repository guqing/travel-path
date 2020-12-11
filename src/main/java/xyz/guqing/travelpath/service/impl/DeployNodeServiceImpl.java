package xyz.guqing.travelpath.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.mapper.DeployNodeMapper;
import xyz.guqing.travelpath.model.entity.DeployPlanNode;
import xyz.guqing.travelpath.service.DeployNodeService;

/**
 * @author guqing
 * @date 2020-10-26
 */
@Service
public class DeployNodeServiceImpl extends ServiceImpl<DeployNodeMapper, DeployPlanNode> implements DeployNodeService {

}
