package com.tt.base.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tt.base.Util.JsonUtil;
import com.tt.base.cotent.RestApi;

public class ResponseBean {

    private String msg;
    private int code;
    private Object data;

    /**
     * 总纪录数
     */
    private long total;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    private ResponseBean() {
    }

    public ResponseBean(String msg, int code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public ResponseBean(String msg, int code, Object data, long total) {
        this.msg = msg;
        this.code = code;
        this.data = data;
        this.total = total;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toJSONString() {
        JSONObject json = new JSONObject();
        json.put(RestApi.RequestKey.CODE, this.code);
        json.put(RestApi.RequestKey.MSG, this.msg);
        json.put(RestApi.RequestKey.DATA, this.data);
        return JSON.toJSONString(json, SerializerFeature.WriteMapNullValue);
    }

    public static ResponseBean paramerError() {
        ResponseBean reb = new ResponseBean();
        reb.msg = RestApi.Msg.PARAMER_ERROR;
        reb.code = RestApi.Code.PARAMER_ERROR;
        reb.data = null;
        return reb;
    }

    public static ResponseBean paramerFail() {
        ResponseBean reb = new ResponseBean();
        reb.msg = RestApi.Msg.FAIL;
        reb.code = RestApi.Code.FAIL;
        reb.data = null;
        return reb;
    }

    //test git
    public static ResponseBean paramerNotData() {
        ResponseBean reb = new ResponseBean();
        reb.msg = RestApi.Msg.NOT_DATA;
        reb.code = RestApi.Code.NOT_DATA;
        reb.data = null;
        return reb;
    }

    public static ResponseBean parse(String jsonText) {
        String msg = JsonUtil.getStringValue(jsonText, RestApi.RequestKey.MSG);
        int code = JsonUtil.getIntValue(jsonText, RestApi.RequestKey.CODE);
        Object data = JsonUtil.getObjectValue(jsonText, RestApi.RequestKey.DATA);
        return new ResponseBean(msg, code, data);
    }

}
