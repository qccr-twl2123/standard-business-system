package com.business.system.common.entity;

import java.io.Serializable;
import java.util.Date;

public class PersonInfo implements Serializable {
    private Long id;

    private String name;

    private String code;

    private Byte type;

    private Byte status;

    private Long departmentId;

    private String doorRights;

    private String masterImageUrl;

    private String materRect;

    private String createPerson;

    private Date createTime;

    private String updatePerson;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDoorRights() {
        return doorRights;
    }

    public void setDoorRights(String doorRights) {
        this.doorRights = doorRights == null ? null : doorRights.trim();
    }

    public String getMasterImageUrl() {
        return masterImageUrl;
    }

    public void setMasterImageUrl(String masterImageUrl) {
        this.masterImageUrl = masterImageUrl == null ? null : masterImageUrl.trim();
    }

    public String getMaterRect() {
        return materRect;
    }

    public void setMaterRect(String materRect) {
        this.materRect = materRect == null ? null : materRect.trim();
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson == null ? null : updatePerson.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", doorRights=").append(doorRights);
        sb.append(", masterImageUrl=").append(masterImageUrl);
        sb.append(", materRect=").append(materRect);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", createTime=").append(createTime);
        sb.append(", updatePerson=").append(updatePerson);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
