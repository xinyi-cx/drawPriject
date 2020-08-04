package com.tt.manage.entity;

public class DrawConfigVip {
    private String drawId;

    private String digit;

    private Integer digitStart;

    private Integer digitEnd;

    public String getDrawId() {
        return drawId;
    }

    public void setDrawId(String drawId) {
        this.drawId = drawId == null ? null : drawId.trim();
    }

    public String getDigit() {
        return digit;
    }

    public void setDigit(String digit) {
        this.digit = digit == null ? null : digit.trim();
    }

    public Integer getDigitStart() {
        return digitStart;
    }

    public void setDigitStart(Integer digitStart) {
        this.digitStart = digitStart;
    }

    public Integer getDigitEnd() {
        return digitEnd;
    }

    public void setDigitEnd(Integer digitEnd) {
        this.digitEnd = digitEnd;
    }
}