package xyz.guqing.travelpath.entity.model;

import java.util.Date;

public class Routes {
    private Long id;

    private Integer distance;

    private String policy;

    private Integer time;

    private Integer tolls;

    private Integer tollsDistance;

    private String origin;

    private String destination;

    private Integer state;

    private Integer actionCount;

    private Long rid;

    private Integer userid;

    private Date createTime;

    private Date modifyTime;

    private Boolean deleted;

    private byte[] path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy == null ? null : policy.trim();
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getTolls() {
        return tolls;
    }

    public void setTolls(Integer tolls) {
        this.tolls = tolls;
    }

    public Integer getTollsDistance() {
        return tollsDistance;
    }

    public void setTollsDistance(Integer tollsDistance) {
        this.tollsDistance = tollsDistance;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getActionCount() {
        return actionCount;
    }

    public void setActionCount(Integer actionCount) {
        this.actionCount = actionCount;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public byte[] getPath() {
        return path;
    }

    public void setPath(byte[] path) {
        this.path = path;
    }
}