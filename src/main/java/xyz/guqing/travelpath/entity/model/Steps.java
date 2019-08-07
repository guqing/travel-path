package xyz.guqing.travelpath.entity.model;

import java.util.Date;

public class Steps {
    private Long id;

    private String action;

    private String assistantAction;

    private String cities;

    private Integer distance;

    private String instruction;

    private String road;

    private Integer time;

    private Long routeId;

    private Date createTime;

    private Date modifyTime;

    private byte[] path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getAssistantAction() {
        return assistantAction;
    }

    public void setAssistantAction(String assistantAction) {
        this.assistantAction = assistantAction == null ? null : assistantAction.trim();
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities == null ? null : cities.trim();
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction == null ? null : instruction.trim();
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road == null ? null : road.trim();
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public byte[] getPath() {
        return path;
    }

    public void setPath(byte[] path) {
        this.path = path;
    }
}