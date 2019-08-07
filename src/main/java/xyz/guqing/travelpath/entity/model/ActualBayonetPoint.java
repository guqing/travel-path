package xyz.guqing.travelpath.entity.model;

import java.util.Date;

public class ActualBayonetPoint {
    private Long id;

    private String lng;

    private String lat;

    private String edgeNumber;

    private Long actualId;

    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getEdgeNumber() {
        return edgeNumber;
    }

    public void setEdgeNumber(String edgeNumber) {
        this.edgeNumber = edgeNumber == null ? null : edgeNumber.trim();
    }

    public Long getActualId() {
        return actualId;
    }

    public void setActualId(Long actualId) {
        this.actualId = actualId;
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
}