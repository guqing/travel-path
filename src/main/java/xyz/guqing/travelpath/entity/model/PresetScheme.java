package xyz.guqing.travelpath.entity.model;

import java.util.Date;

public class PresetScheme {
    private Long id;

    private String name;

    private String description;

    private Integer bayonetCount;

    private Integer userid;

    private Date createTime;

    private Date modifyTime;

    private Byte deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getBayonetCount() {
        return bayonetCount;
    }

    public void setBayonetCount(Integer bayonetCount) {
        this.bayonetCount = bayonetCount;
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

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "PresetScheme{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", bayonetCount=" + bayonetCount +
                ", userid=" + userid +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", deleted=" + deleted +
                '}';
    }
}