package xyz.guqing.travelpath.entity.model;

import java.util.Date;

public class PermissionAction {
    private Integer id;

    private String action;

    private String description;

    private Boolean defaultcheck;

    private String url;

    private Integer pId;

    private Date createTime;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getDefaultcheck() {
        return defaultcheck;
    }

    public void setDefaultcheck(Boolean defaultcheck) {
        this.defaultcheck = defaultcheck;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
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
    public int hashCode() {
        return id + action.hashCode() + url.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj instanceof PermissionAction) {
            PermissionAction permissionAction = (PermissionAction) obj;
            if(this.id.equals(permissionAction.id)
                    && this.action==permissionAction.action
                    && this.url.equals(permissionAction.url)) {
                return true;
            }
            return false;
        }

        return false;
    }
}