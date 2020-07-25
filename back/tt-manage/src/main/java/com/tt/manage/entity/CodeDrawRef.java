package com.tt.manage.entity;

public class CodeDrawRef {
    private String refId;

    private Long code;

    private String drawDigit;

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId == null ? null : refId.trim();
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDrawDigit() {
        return drawDigit;
    }

    public void setDrawDigit(String drawDigit) {
        this.drawDigit = drawDigit == null ? null : drawDigit.trim();
    }
}