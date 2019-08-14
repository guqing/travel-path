package xyz.guqing.travelpath.entity.model;

import java.util.Date;

public class Presetpoint {
    private Long id;

    private String lng;

    private String lat;

    private String edgeNumber;

    private Long preid;

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

    public Long getPreid() {
        return preid;
    }

    public void setPreid(Long preid) {
        this.preid = preid;
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

    @Override
    public String toString() {
        return "Presetpoint{" +
                "id=" + id +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                ", edgeNumber='" + edgeNumber + '\'' +
                ", preid=" + preid +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}