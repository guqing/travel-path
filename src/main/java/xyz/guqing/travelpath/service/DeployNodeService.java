package xyz.guqing.travelpath.service;

import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author guqing
 * @date 2020-10-26
 */
public interface DeployNodeService {
    /**
     * 创建或更新方案
     * @param planId 方案id
     * @param nodeIds 卡口信息id
     */
    void createOrUpdate(@NonNull Long planId, @NonNull List<Long> nodeIds);
}
