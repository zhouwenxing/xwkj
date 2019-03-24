package com.ylzs.core.model;

public class UserLevel {
    private String id;

    private Integer level;

    private String levelValue;

    private String levelDescribe;

    private Integer altitudeFrom;

    private Integer altitudeTo;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue == null ? null : levelValue.trim();
    }

    public String getLevelDescribe() {
        return levelDescribe;
    }

    public void setLevelDescribe(String levelDescribe) {
        this.levelDescribe = levelDescribe == null ? null : levelDescribe.trim();
    }

    public Integer getAltitudeFrom() {
        return altitudeFrom;
    }

    public void setAltitudeFrom(Integer altitudeFrom) {
        this.altitudeFrom = altitudeFrom;
    }

    public Integer getAltitudeTo() {
        return altitudeTo;
    }

    public void setAltitudeTo(Integer altitudeTo) {
        this.altitudeTo = altitudeTo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}